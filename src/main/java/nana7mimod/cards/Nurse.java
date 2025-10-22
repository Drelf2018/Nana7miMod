package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;

public class Nurse extends Base {
    public static final String ID = ModHelper.id(Nurse.class);

    public Nurse() {
        super(ID, CardCost.C1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 4;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeMagicNumber(1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playSound(ID);
        addToBot(new DamageAction(m, new DamageInfo(p, damage), AttackEffect.BLUNT_LIGHT));
        addToBot(new HealAction(p, p, magicNumber));
    }
}
