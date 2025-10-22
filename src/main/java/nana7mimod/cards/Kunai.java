package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;

public class Kunai extends Base {
    public static final String ID = ModHelper.id(Kunai.class);

    public static final int ACTUAL_BASE_DAMAGE = 4;

    public static final int UPGRADE_BASE_DAMAGE = 2;

    public Kunai() {
        super(ID, CardCost.C1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = ACTUAL_BASE_DAMAGE;
        this.magicNumber = this.baseMagicNumber = 4;
        this.returnToHand = true;
    }

    // 玩家回合结束重置伤害
    @Override
    public void triggerOnEndOfPlayerTurn() {
        baseDamage = ACTUAL_BASE_DAMAGE + (upgraded ? UPGRADE_BASE_DAMAGE : 0);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_BASE_DAMAGE);
            upgradeMagicNumber(2);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage), AttackEffect.SLASH_DIAGONAL));
        baseDamage += magicNumber;
    }
}
