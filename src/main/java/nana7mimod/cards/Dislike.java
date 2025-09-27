package nana7mimod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.InjuredPower;

public class Dislike extends Base {
    public static final String ID = ModHelper.id(Dislike.class);

    public Dislike() {
        super(ID, CardCost.C1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 5;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        p.getPower(InjuredPower.POWER_ID).reducePower(magicNumber);
    }
}
