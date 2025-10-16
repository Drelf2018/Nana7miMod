package nana7mimod.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;
import java.util.ArrayList;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import nana7mimod.helpers.ModHelper;
import nana7mimod.patches.AbstractPowerPatch;

public class InjuredPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.id(InjuredPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int lostHPLimit;

    private static ArrayList<AbstractCreature> attackedCreatures = new ArrayList<>();

    public InjuredPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.lostHPLimit = amount;

        updateDescription();
        AbstractPowerPatch.loadRegion(this, "injured");
    }

    // 受击
    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageType.NORMAL && info.owner != owner && !attackedCreatures.contains(info.owner)) {
            flashWithoutSound();
            ATFieldPower.addAmount(owner, 1);
            attackedCreatures.add(info.owner);
        }
        return damageAmount;
    }

    // 受伤
    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        flashWithoutSound();
        int lostHP = lostHPLimit - amount + damageAmount;
        ATFieldPower.addAmount(owner, lostHP / lostHPLimit);
        amount = lostHPLimit - (lostHP % lostHPLimit);
        updateDescription();
    }

    // 回合开始重置已攻击敌人列表
    @Override
    public void atStartOfTurn() {
        attackedCreatures.clear();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
