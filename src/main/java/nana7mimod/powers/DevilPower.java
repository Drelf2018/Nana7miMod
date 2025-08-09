package nana7mimod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import nana7mimod.helpers.ModHelper;

public class DevilPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.id(DevilPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DevilPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        loadRegion("darkembrace"); // 换成😭
        updateDescription();
    }

    // 获取角色走火入魔
    public static DevilPower from(AbstractCreature owner) {
        AbstractPower power = owner.getPower(POWER_ID);
        if (power instanceof DevilPower) {
            return (DevilPower) power;
        }
        return null;
    }

    // 获取角色走火入魔程度
    public static int getAmount(AbstractCreature target) {
        DevilPower power = DevilPower.from(target);
        if (power != null) {
            return power.amount;
        }
        return 0;
    }

    // 更新描述
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
