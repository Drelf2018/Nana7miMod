package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import nana7mimod.helpers.ModHelper;

public class BetrayPower extends Base {
    public static final String POWER_ID = ModHelper.id(BetrayPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int delayAmount;

    public BetrayPower(AbstractCreature owner, int amount, boolean delay) {
        super(POWER_ID, powerStrings.NAME, owner, delay ? 0 : amount, PowerType.BUFF);
        this.isTurnBased = true;
        this.delayAmount = delay ? amount : 0;
        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        if (stackAmount == 0) {
            delayAmount = 4 + amount + delayAmount;
            amount = 0;
        } else if (delayAmount != 0) {
            delayAmount += stackAmount;
        } else {
            amount += stackAmount;
        }
        flashWithoutSound();
        updateDescription();
    }

    @Override
    public void atEndOfRound() {
        AbstractPower p = owner.getPower(InfatuationPower.POWER_ID);
        if (p instanceof InfatuationPower) {
            if (p.amount == 1) {
                amount = delayAmount;
                delayAmount = 0;
                updateDescription();
            }
        } else {
            addToBot(new ReducePowerAction(owner, owner, this, 1));
        }
    }

    @Override
    public float atDamageGive(float damage, DamageType type) {
        if (type == DamageType.NORMAL && amount > 0)
            return damage + amount;
        return damage;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (amount > 0)
            flashWithoutSound();
    }

    @Override
    public void updateDescription() {
        if (amount == 0) {
            name = DESCRIPTIONS[2] + powerStrings.NAME;
            description = DESCRIPTIONS[2] + " " + DESCRIPTIONS[0] + delayAmount + DESCRIPTIONS[1];
        } else {
            name = powerStrings.NAME;
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }
    }
}
