package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.ATFieldPower;

public class EnergyDrink extends Base {
    public static final String ID = ModHelper.id(EnergyDrink.class);

    public EnergyDrink() {
        super(ID, CardCost.C0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playSound(ID);
        if (upgraded)
            addToBot(new GainEnergyAction(3));
        else
            addToBot(new GainEnergyAction(2));
        ATFieldPower.addAmount(p, -magicNumber);
    }
}
