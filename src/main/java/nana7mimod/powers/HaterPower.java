package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import nana7mimod.cards.Mark;
import nana7mimod.helpers.ModHelper;
import nana7mimod.patches.AbstractPowerPatch;

public class HaterPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.id(HaterPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean hasTriggeredThisTurn = false;

    public HaterPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;

        updateDescription();
        AbstractPowerPatch.loadRegion(this, "hater");
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (!hasTriggeredThisTurn && info.type == DamageType.NORMAL) {
            flash();
            addToTop(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(amount, true), DamageType.THORNS, AttackEffect.FIRE));
            hasTriggeredThisTurn = true;
        }
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageType.NORMAL)
            for (AbstractCard c : AbstractDungeon.player.discardPile.group)
                if (c.cardID.equals(Mark.ID))
                    addToBot(new DiscardToHandAction(c));
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        hasTriggeredThisTurn = false;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
