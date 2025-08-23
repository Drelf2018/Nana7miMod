package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import nana7mimod.actions.LightningAction;
import nana7mimod.helpers.ModHelper;

public class GuiltyPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.id(GuiltyPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static final int REDUCE = 30;

    private AbstractCreature source;

    public GuiltyPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.source = source;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;

        updateDescription();
        loadRegion("poison"); // 换成😭
    }

    // 天雷滚滚我好怕怕
    // 做亏心事遭雷劈很合理好吧
    @Override
    public void atStartOfTurn() {
        flashWithoutSound();
        addToBot(new LightningAction(owner, new DamageInfo(source, amount, DamageType.HP_LOSS)));
        addToBot(new ReducePowerAction(owner, owner, this, REDUCE));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
