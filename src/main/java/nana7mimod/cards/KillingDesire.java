package nana7mimod.cards;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.actions.KillingAction;
import nana7mimod.helpers.ModHelper;

public class KillingDesire extends Base {
    public static final String ID = ModHelper.id(KillingDesire.class);

    public KillingDesire() {
        super(ID, CardCost.C1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.damage = this.baseDamage = 4;
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            this.exhaust = false;
            upgradeName();
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new KillingAction(m, new DamageInfo(p, damage), magicNumber));
    }
}
