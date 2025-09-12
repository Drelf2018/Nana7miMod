package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.AntiGravityPower;

public class Angelina extends Base {
    public static final String ID = ModHelper.id(Angelina.class);

    public Angelina() {
        super(ID, CardCost.C3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(cost - 1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AntiGravityPower(p, magicNumber)));
    }
}
