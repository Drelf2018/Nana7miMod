package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import nana7mimod.cards.Back;
import nana7mimod.helpers.ModHelper;

public class DormantPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.id(DormantPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DormantPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;

        updateDescription();
        loadRegion("confusion"); // 换成ZZZ
    }

    @Override
    public boolean canPlayCard(AbstractCard card) {
        return amount != 0;
    }

    @Override
    public void atStartOfTurn() {
        if (amount > 0) {
            amount--;
            updateDescription();
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer && amount == 0) {
            flashWithoutSound();
            addToBot(new MakeTempCardInDiscardAction(new Back(), 1));
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 0) {
            description = DESCRIPTIONS[3];
        } else if (amount == 1) {
            description = DESCRIPTIONS[2];
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }
    }
}
