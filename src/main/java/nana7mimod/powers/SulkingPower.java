package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import nana7mimod.helpers.ModHelper;
import nana7mimod.patches.AbstractPowerPatch;

public class SulkingPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.id(SulkingPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SulkingPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;

        updateDescription();
        AbstractPowerPatch.loadRegion(this, "sulking");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            flashWithoutSound();
            addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, -amount)));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
