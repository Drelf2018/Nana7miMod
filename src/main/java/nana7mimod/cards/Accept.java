package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.ATFieldPower;

public class Accept extends Base {
    public static final String ID = ModHelper.id(Accept.class);

    public Accept() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(this.cost - 1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ATFieldPower power = ATFieldPower.from(p);
        if (power != null && power.amount > 0) {
            addToBot(new GainEnergyAction(power.amount));
            power.addAmount(0 - power.amount, true);
        }
    }
}
