package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;

public class NightPower extends Base {
    public static final String POWER_ID = ModHelper.id(NightPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NightPower(AbstractCreature owner, int amount) {
        super(POWER_ID, powerStrings.NAME, owner, amount, PowerType.BUFF);
    }

    // 群体失落
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            flashWithoutSound();
            for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters)
                addToBot(new ApplyPowerAction(mo, owner, new LostPower(mo, amount)));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
