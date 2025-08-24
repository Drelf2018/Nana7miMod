package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import nana7mimod.helpers.ModHelper;

public class InfatuationPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.id(InfatuationPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public InfatuationPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;

        updateDescription();
        loadRegion("weak"); // 换成😭
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (amount >= 4) {
            amount = 4;
        }
    }

    @Override
    public void atEndOfRound() {
        addToBot(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public float atDamageGive(float damage, DamageType type) {
        return type == DamageType.NORMAL ? damage * (1 - amount / 4.0F) : damage;
    }

    @Override
    public void onRemove() {
        addToBot(new ApplyPowerAction(owner, owner, new BetrayPower(owner, 5)));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[1] + 25 * amount + DESCRIPTIONS[2];
        if (amount == 1) {
            description = DESCRIPTIONS[0] + description;
        }
    }
}
