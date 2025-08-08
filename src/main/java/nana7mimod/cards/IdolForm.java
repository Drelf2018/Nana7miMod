package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.helpers.BaseModCardTags;
import nana7mimod.actions.ExhaustAllAction;
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

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(this.cost - 1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RemoveSpecificPowerAction(p, p, InjuredPower.POWER_ID));
        addToBot(new RemoveSpecificPowerAction(p, p, ATFieldPower.POWER_ID));
        addToBot(new ExhaustAllAction(p.hand, CardType.ATTACK));
        addToBot(new ExhaustAllAction(p.drawPile, CardType.ATTACK));
        addToBot(new ExhaustAllAction(p.discardPile, CardType.ATTACK));
        addToBot(new ApplyPowerAction(p, p, new FirmPower(p)));
        addToBot(new ApplyPowerAction(p, p, new PityingPower(p)));
    }
}
