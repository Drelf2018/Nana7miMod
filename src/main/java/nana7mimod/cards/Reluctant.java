package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.BetrayPower;
import nana7mimod.powers.InfatuationPower;

public class Reluctant extends Base {
    public static final String ID = ModHelper.id(Reluctant.class);

    public Reluctant() {
        super(ID, CardCost.C2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 3;
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playSound(ID);
        addToBot(new ApplyPowerAction(m, p, new InfatuationPower(m, magicNumber)));
        addToBot(new ApplyPowerAction(m, p, new BetrayPower(m, 4, true)));
    }
}
