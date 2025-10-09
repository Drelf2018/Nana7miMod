package nana7mimod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.ATFieldPower;

public class Furious extends Base {
    public static final String ID = ModHelper.id(Furious.class);

    public Furious() {
        super(ID, CardCost.C1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 4;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ATFieldPower.addAmount(p, p.drawPile.size() / magicNumber);
    }
}
