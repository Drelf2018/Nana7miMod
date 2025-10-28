package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.PadoruPower;

public class Padoru extends Base {
    public static final String ID = ModHelper.id(Padoru.class);

    public Padoru() {
        super(ID, CardCost.C1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(cost - 1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PadoruPower(p, magicNumber)));
    }
}
