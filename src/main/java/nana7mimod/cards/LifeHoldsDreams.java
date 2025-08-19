package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.DormantPower;
import nana7mimod.powers.LostPower;

public class LifeHoldsDreams extends Base {
    public static final String ID = ModHelper.id(LifeHoldsDreams.class);

    public LifeHoldsDreams() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 5;
        this.cardsToPreview = new Back();
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new LostPower(m, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new DormantPower(p, 2)));
    }
}
