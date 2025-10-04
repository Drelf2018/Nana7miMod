package nana7mimod.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import nana7mimod.helpers.ModHelper;
import nana7mimod.patches.AbstractPowerPatch;

public class InjuredPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.id(InjuredPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int lostHPLimit;

    public InjuredPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.lostHPLimit = amount;

        updateDescription();
        AbstractPowerPatch.loadRegion(this, "injured");
    }

    @Override
    public void reducePower(int reduceAmount) {
        lostHPLimit -= reduceAmount;
        if (lostHPLimit < 1) {
            lostHPLimit = 1;
        }
        wasHPLost(null, reduceAmount);
    }

    // 受伤
    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        flashWithoutSound();
        int lostHP = lostHPLimit - amount + damageAmount;
        ATFieldPower.addAmount(owner, lostHP / lostHPLimit);
        amount = lostHPLimit - (lostHP % lostHPLimit);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
