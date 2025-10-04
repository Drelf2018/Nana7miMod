package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.HometownPower;

public class Hometown extends Base {
    public static final String ID = ModHelper.id(Hometown.class);

    public Hometown() {
        super(ID, CardCost.C1, CardType.POWER, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 10;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(5);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HometownPower(p, magicNumber)));
    }
}
