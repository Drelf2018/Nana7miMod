package nana7mimod.cards;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.actions.KillingAction;
import nana7mimod.helpers.ModHelper;

public class KillingDesire extends Base {
    public static final String ID = ModHelper.id(KillingDesire.class);

    public KillingDesire() {
        super(ID, CardCost.C1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.damage = this.baseDamage = 3;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new KillingAction(m, new DamageInfo(p, damage, DamageType.NORMAL), magicNumber));
    }
}
