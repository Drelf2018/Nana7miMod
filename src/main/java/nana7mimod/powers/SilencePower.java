package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import nana7mimod.helpers.ModHelper;

public class SilencePower extends Base {
    public static final String POWER_ID = ModHelper.id(SilencePower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SilencePower(AbstractCreature owner, int amount) {
        super(POWER_ID, powerStrings.NAME, owner, amount, PowerType.DEBUFF);
        this.isTurnBased = true;
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
        return 0;
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        return 0;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        flashWithoutSound();
        return damageAmount;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        flashWithoutSound();
    }

    @Override
    public void atEndOfRound() {
        addToBot(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
