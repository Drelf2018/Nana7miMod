package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import nana7mimod.actions.LightningAction;
import nana7mimod.helpers.ModHelper;

public class GuiltyPower extends Base {
    public static final String POWER_ID = ModHelper.id(GuiltyPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private AbstractCreature source;

    public GuiltyPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, powerStrings.NAME, owner, amount, PowerType.DEBUFF, "storm");
        this.source = source;
        this.isTurnBased = true;
    }

    // 天雷滚滚我好怕怕
    // 做亏心事遭雷劈很合理好吧
    @Override
    public void atStartOfTurn() {
        flashWithoutSound();
        addToTop(new ReducePowerAction(owner, owner, this, 30));
        addToTop(new LightningAction(owner, new DamageInfo(source, amount, DamageType.HP_LOSS)));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
