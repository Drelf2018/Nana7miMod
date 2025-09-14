package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.SurroundedPower;
import nana7mimod.helpers.ModHelper;

public class BackAttackPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.id(BackAttackPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private float drawX;
    private float drawY;
    private boolean flipHorizontal;
    private boolean hasSurrounded;

    public BackAttackPower(AbstractPlayer owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.drawX = owner.drawX;
        this.drawY = owner.drawY;
        this.flipHorizontal = owner.flipHorizontal;
        this.hasSurrounded = owner.hasPower(SurroundedPower.POWER_ID);

        updateDescription();
        loadRegion("backAttack2");
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.type == DamageType.NORMAL && owner.hb.cX - target.hb.cX > 0 == owner.flipHorizontal) {
            flash();
            addToBot(new DamageAction(target, new DamageInfo(owner, damageAmount, DamageType.THORNS)));
            addToBot(new ReducePowerAction(owner, owner, this, 1));
        }
    }

    @Override
    public void onRemove() {
        AbstractPlayer p = (AbstractPlayer) owner;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                p.flipHorizontal = flipHorizontal;
                p.movePosition(drawX, drawY);
                isDone = true;
            }
        });
        if (hasSurrounded) {
            addToBot(new ApplyPowerAction(p, p, new SurroundedPower(p)));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
