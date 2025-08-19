package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import com.megacrit.cardcrawl.core.Settings;
import basemod.helpers.BaseModCardTags;
import nana7mimod.actions.ExhaustAllAction;
import nana7mimod.actions.IdolAction;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.InjuredPower;
import nana7mimod.powers.PityingPower;
import nana7mimod.powers.ATFieldPower;
import nana7mimod.powers.FirmPower;

public class IdolForm extends Base {
    public static final String ID = ModHelper.id(IdolForm.class);

    public IdolForm() {
        super(ID, CardCost.C5, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(BaseModCardTags.FORM);
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
        if (Settings.FAST_MODE) {
            addToBot(new VFXAction(new GrandFinalEffect(), 0.7F));
        } else {
            addToBot(new VFXAction(new GrandFinalEffect(), 1.0F));
        }
        addToBot(new RemoveSpecificPowerAction(p, p, InjuredPower.POWER_ID));
        addToBot(new RemoveSpecificPowerAction(p, p, ATFieldPower.POWER_ID));
        addToBot(new ApplyPowerAction(p, p, new FirmPower(p)));
        addToBot(new ApplyPowerAction(p, p, new PityingPower(p)));
        addToBot(new IdolAction());
    }
}
