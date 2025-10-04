package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import nana7mimod.helpers.ModHelper;
import nana7mimod.patches.AbstractPowerPatch;

public class FirmPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.id(FirmPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FirmPower(AbstractCreature owner) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        this.description = DESCRIPTIONS[0];

        AbstractPowerPatch.loadRegion(this, "firm");
    }

    // 格挡翻倍
    @Override
    public float modifyBlockLast(float blockAmount) {
        return 2 * blockAmount;
    }

    // 回合开始时回复当前格挡值的生命
    @Override
    public void atStartOfTurn() {
        if (owner.currentBlock != 0) {
            flashWithoutSound();
            addToTop(new HealAction(owner, owner, owner.currentBlock));
        }
    }
}
