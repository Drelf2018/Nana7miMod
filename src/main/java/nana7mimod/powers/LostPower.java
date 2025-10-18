package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import nana7mimod.helpers.ModHelper;

public class LostPower extends Base {
    public static final String POWER_ID = ModHelper.id(LostPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LostPower(AbstractCreature owner, int amount) {
        super(POWER_ID, powerStrings.NAME, owner, amount, PowerType.DEBUFF);
        this.isTurnBased = true;
    }

    // 获取角色失落
    public static boolean has(AbstractCreature target) {
        return target.getPower(POWER_ID) instanceof LostPower;
    }

    // 上一轮结束后获得能量
    @Override
    public void atEndOfRound() {
        flash();
        addToBot(new GainEnergyAction(amount));
        addToBot(new ReducePowerAction(owner, owner, this, 1));
    }

    // 死亡时获得能量
    @Override
    public void onDeath() {
        flash();
        addToBot(new GainEnergyAction(1));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
