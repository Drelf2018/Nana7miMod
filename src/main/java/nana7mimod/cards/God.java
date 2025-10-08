package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.ATFieldPower;

public class God extends Base {
    public static final String ID = ModHelper.id(God.class);

    public God() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
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
            for (int i = 0; i < amount; ++i)
                addToBot(new ApplyPowerAction(p, p, new MantraPower(p, magicNumber)));
            return 0;
        });
    }
}
