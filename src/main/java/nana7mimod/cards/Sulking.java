package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.SulkingPower;

public class Sulking extends Base {
    public static final String ID = ModHelper.id(Sulking.class);

    public Sulking() {
        super(ID, CardCost.C1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(cost - 1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SulkingPower(p, 1)));
    }
}
