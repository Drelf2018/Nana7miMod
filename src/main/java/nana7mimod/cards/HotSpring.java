package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.HotSpringPower;

public class HotSpring extends Base {
    public static final String ID = ModHelper.id(HotSpring.class);

    public HotSpring() {
        super(ID, CardCost.C0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HotSpringPower(p, magicNumber)));
    }
}
