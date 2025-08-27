package nana7mimod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.ATFieldPower;

public class Disappointed extends Base {
    public static final String ID = ModHelper.id(Disappointed.class);

    public Disappointed() {
        super(ID, CardCost.C2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ATFieldPower.setAmount(p, amount -> {
            if (amount <= 0)
                return amount;
            return amount * magicNumber;
        });
    }
}
