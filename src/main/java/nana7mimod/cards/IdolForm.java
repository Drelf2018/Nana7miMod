package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import basemod.helpers.BaseModCardTags;
import nana7mimod.actions.ExhaustAllAction;
import nana7mimod.helpers.ModHelper;
import nana7mimod.patches.OnEnterRoomPatch.ClothingHandler;
import nana7mimod.powers.InjuredPower;
import nana7mimod.powers.PityingPower;
import nana7mimod.powers.ShinePower;
import nana7mimod.powers.ATFieldPower;
import nana7mimod.powers.FirmPower;

public class IdolForm extends Base {
    public static final String ID = ModHelper.id(IdolForm.class);

    public IdolForm() {
        super(ID, CardCost.C5, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(BaseModCardTags.FORM);
        this.keywords.add("nana7mimod:愧疚");
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(cost - 1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExhaustAllAction(p.hand, CardType.ATTACK));
        addToBot(new ExhaustAllAction(p.drawPile, CardType.ATTACK));
        addToBot(new ExhaustAllAction(p.discardPile, CardType.ATTACK));
        // 保存当前心之壁层数
        ATFieldPower power = ATFieldPower.from(p);
        int amount = power == null ? 0 : power.amount;
        // 移除心之壁能力
        addToBot(new RemoveSpecificPowerAction(p, p, InjuredPower.POWER_ID));
        addToBot(new RemoveSpecificPowerAction(p, p, ATFieldPower.POWER_ID));
        // 根据原先是否有心之壁判断是否要播放变装动画
        if (power != null) {
            addToBot(new VFXAction(new GrandFinalEffect(), 1.0F));
            if (p instanceof ClothingHandler)
                ((ClothingHandler) p).PutOnClothes();
        }
        // 获得等量闪耀
        if (amount > 0)
            addToBot(new ApplyPowerAction(p, p, new ShinePower(p, amount)));
        if (p.getPower(FirmPower.POWER_ID) == null)
            addToBot(new ApplyPowerAction(p, p, new FirmPower(p)));
        if (p.getPower(PityingPower.POWER_ID) == null)
            addToBot(new ApplyPowerAction(p, p, new PityingPower(p)));
    }
}
