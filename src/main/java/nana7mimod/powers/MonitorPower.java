package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import nana7mimod.helpers.ModHelper;

public class MonitorPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.id(MonitorPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MonitorPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = amount == -1 ? PowerType.BUFF : PowerType.DEBUFF;

        updateDescription();
        loadRegion("lockon"); // 换成😭
    }

    @Override
    public void atStartOfTurnPostDraw() {
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (m.getIntentBaseDmg() >= 0) {
                AbstractPower power = m.getPower(POWER_ID);
                if (power instanceof MonitorPower) {
                    power.flash();
                    addToBot(new DrawCardAction(power.amount));
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        if (type == PowerType.DEBUFF)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        else
            description = DESCRIPTIONS[2];
    }
}
