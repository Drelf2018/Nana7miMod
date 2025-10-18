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

    public BetrayPower(AbstractCreature owner, int amount, boolean delay) {
        super(POWER_ID, delay ? DESCRIPTIONS[2] + powerStrings.NAME : powerStrings.NAME, owner, amount, PowerType.BUFF);
        this.isTurnBased = true;
        if (delay)
            this.description = DESCRIPTIONS[0] + 0 + DESCRIPTIONS[1];
        else
            updateDescription();
    }

    @Override
    public void atEndOfRound() {
        AbstractPower p = owner.getPower(InfatuationPower.POWER_ID);
        if (p instanceof InfatuationPower) {
            if (p.amount == 1) {
                name = powerStrings.NAME;
                description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
            }
        } else {
            addToBot(new ReducePowerAction(owner, owner, this, 1));
        }
    }

    @Override
    public float atDamageGive(float damage, DamageType type) {
        if (type != DamageType.NORMAL || owner.getPower(InfatuationPower.POWER_ID) instanceof InfatuationPower)
            return damage;
        return damage + amount;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        flashWithoutSound();
    }

    @Override
    public void updateDescription() {
        if (owner.getPower(InfatuationPower.POWER_ID) instanceof InfatuationPower) {
            name = DESCRIPTIONS[2] + powerStrings.NAME;
            description = DESCRIPTIONS[0] + 0 + DESCRIPTIONS[1];
        } else {
            name = powerStrings.NAME;
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }
    }
}
