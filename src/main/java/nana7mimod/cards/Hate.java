package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;

public class Hate extends Base {
    public static final String ID = ModHelper.id(Hate.class);

    public Hate(int upgrades) {
        super(ID, CardCost.C1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.baseDamage = 1;
        this.magicNumber = this.baseMagicNumber = 1;
        this.timesUpgraded = upgrades;
    }

    public Hate() {
        this(0);
    }

    @Override
    public void upgrade() {
        upgradeDamage(1 + this.timesUpgraded);
        upgradeMagicNumber(1);
        this.timesUpgraded++;
        this.upgraded = true;
        this.name = getName(ID) + "+" + this.timesUpgraded;
        initializeTitle();
    }

    public boolean canUpgrade() {
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageType.NORMAL), AttackEffect.BLUNT_LIGHT));
    }
}
