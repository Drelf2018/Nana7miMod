package nana7mimod.powers;

import java.util.ArrayList;
import java.util.function.Function;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import nana7mimod.helpers.ModHelper;

public class ATFieldPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.id(ATFieldPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static ArrayList<AbstractCreature> attackedCreatures = new ArrayList<>();

    public ATFieldPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.canGoNegative = true;

        updateDescription();
        loadRegion("strength");
    }

    // 获取角色心之壁
    public static ATFieldPower from(AbstractCreature owner) {
        AbstractPower power = owner.getPower(POWER_ID);
        if (power instanceof ATFieldPower) {
            return (ATFieldPower) power;
        }
        return null;
    }

    // 为拥有心之壁的角色设置层数
    public static void setAmount(AbstractCreature target, int newAmount) {
        ATFieldPower power = ATFieldPower.from(target);
        if (power != null) {
            power.addAmount(newAmount - power.amount);
        }
    }

    // 为拥有心之壁的角色设置层数
    public static void setAmount(AbstractCreature target, Function<Integer, Integer> function) {
        ATFieldPower power = ATFieldPower.from(target);
        if (power != null) {
            power.addAmount(function.apply(power.amount) - power.amount);
        }
    }

    // 为拥有心之壁的角色增加层数
    public static void addAmount(AbstractCreature target, int diffAmount) {
        ATFieldPower power = ATFieldPower.from(target);
        if (power != null) {
            power.addAmount(diffAmount);
        }
    }

    // 为拥有心之壁的角色增加层数
    public static void addAmount(AbstractCreature target, Function<Integer, Integer> function) {
        ATFieldPower power = ATFieldPower.from(target);
        if (power != null) {
            power.addAmount(function.apply(power.amount));
        }
    }

    // 更新能力层数
    @Override
    public void stackPower(int stackAmount) {
        amount += stackAmount;
        flashWithoutSound();
        updateDescription();
        addToTop(new DrawCardAction(owner, DevilPower.getAmount(owner)));
        if (stackAmount < 0) {
            onFieldBroken();
        }
    }

    // 添加层数动画
    public void addAmount(int diffAmount, boolean now) {
        if (diffAmount > 0) {
            if (now) {
                addToTop(new ApplyPowerAction(owner, owner, new ATFieldPower(owner, diffAmount)));
            } else {
                addToBot(new ApplyPowerAction(owner, owner, new ATFieldPower(owner, diffAmount)));
            }
        } else if (diffAmount < 0) {
            stackPower(diffAmount);
        }
    }

    // 添加层数动画
    public void addAmount(int diffAmount) {
        addAmount(diffAmount, false);
    }

    // 回合开始重置层数
    @Override
    public void atStartOfTurnPostDraw() {
        addAmount(Math.max(amount, 1) - amount);
        attackedCreatures.clear();
    }

    // 受伤
    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageType.NORMAL && info.owner != owner && !attackedCreatures.contains(info.owner)) {
            addAmount(1);
            attackedCreatures.add(info.owner);
        }
        return damageAmount;
    }

    // 生气
    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == CardType.ATTACK && card.costForTurn != 0 && amount > 0) {
            addAmount(-1);
        }
    }

    // 宽恕
    @Override
    public void onGainedBlock(float blockAmount) {
        addAmount(-1);
    }

    // 破碎
    private void onFieldBroken() {
        switch (amount) {
            case -2:
                for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    addToBot(new ApplyPowerAction(mo, owner, new WeakPower(mo, 1, false)));
                }
                break;
            case -4:
                for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    addToBot(new ApplyPowerAction(mo, owner, new StrengthPower(mo, -2)));
                }
                break;
            case -6:
                addToBot(new ApplyPowerAction(owner, owner, new IntangiblePlayerPower(owner, 1)));
                break;
            default:
                if (amount < 0 && (amount & 1) == 1) {
                    addToBot(new HealAction(owner, owner, -amount));
                }
                break;
        }
    }

    // 增伤
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageType.NORMAL ? damage + amount : damage;
    }

    @Override
    public void updateDescription() {
        if (amount >= 0) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        } else {
            description = DESCRIPTIONS[1] + -amount + DESCRIPTIONS[2];
        }
    }
}
