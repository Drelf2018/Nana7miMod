package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import nana7mimod.helpers.ModHelper;

public class Dumpling extends Base {
    public static final String ID = ModHelper.id(Dumpling.class);

    public Dumpling() {
        super(ID, CardCost.C1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 7;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage), AttackEffect.BLUNT_LIGHT));
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
    }
}
