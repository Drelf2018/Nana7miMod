package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;

public class BouquetPower extends Base {
    public static final String POWER_ID = ModHelper.id(BouquetPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BouquetPower(AbstractCreature owner, int amount) {
        super(POWER_ID, powerStrings.NAME, owner, amount, PowerType.BUFF);
    }

    // 群体入脑
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            int applyCount = 0;
            for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters)
                if (!mo.hasPower(InfatuationPower.POWER_ID)) {
                    applyCount++;
                    addToBot(new ApplyPowerAction(mo, owner, new InfatuationPower(mo, amount)));
                }
            if (applyCount != 0)
                flash();
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
