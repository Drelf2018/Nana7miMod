package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.ATFieldPower;

public class ImpotentRage extends Base {
    public static final String ID = ModHelper.id(ImpotentRage.class);

    public ImpotentRage() {
        super(ID, CardCost.C0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        System.out.println("EnergyPanel.totalCount: " + EnergyPanel.totalCount);
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        if (EnergyPanel.totalCount != 0) {
            canUse = false;
            cantUseMessage = strings(ID).EXTENDED_DESCRIPTION[0];
        }
        return canUse;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DoubleDamagePower(p, 1, false)));
        ATFieldPower.addAmount(p, magicNumber);
    }
}
