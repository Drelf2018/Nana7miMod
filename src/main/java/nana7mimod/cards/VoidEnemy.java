package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.powers.ATFieldPower;
import nana7mimod.helpers.ModHelper;

public class VoidEnemy extends Base {
    public static final String ID = ModHelper.id(VoidEnemy.class);

    public VoidEnemy() {
        super(ID, CardCost.C1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage = 4;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; ++i)
            addToBot(new AttackDamageRandomEnemyAction(this, AttackEffect.SLASH_HORIZONTAL));
        ATFieldPower.addAmount(p, -2);
    }
}
