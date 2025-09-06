package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import nana7mimod.helpers.ModHelper;

public class HeritagePower extends AbstractPower {
    public static final String POWER_ID = ModHelper.id(HeritagePower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int gainGoldAmount;

    public HeritagePower(AbstractCreature owner, int amount, int gainGoldAmount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.gainGoldAmount = gainGoldAmount;

        updateDescription();
        loadRegion("minion"); // 换成😭
    }

    @Override
    public void onDeath() {
        AbstractPlayer p = AbstractDungeon.player;
        p.gainGold(gainGoldAmount);
        for (int i = 0; i < gainGoldAmount; ++i)
            AbstractDungeon.effectList.add(new GainPennyEffect(p, owner.hb.cX, owner.hb.cY, p.hb.cX, p.hb.cY, true));
    }

    @Override
    public void atEndOfRound() {
        addToBot(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + gainGoldAmount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
