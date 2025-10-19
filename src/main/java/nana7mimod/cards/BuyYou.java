package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import nana7mimod.helpers.ModHelper;

public class BuyYou extends Base {
    public static final String ID = ModHelper.id(BuyYou.class);

    public BuyYou() {
        super(ID, CardCost.C0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playSound(ID);
        if (upgraded)
            addToBot(new GainEnergyAction(3));
        else
            addToBot(new GainEnergyAction(2));
        addToBot(new ApplyPowerAction(p, p, new FrailPower(p, magicNumber, false)));
    }
}
