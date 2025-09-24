package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;

public class Kunai extends Base {
    public static final String ID = ModHelper.id(Kunai.class);

    public static final int actualBaseDamage = 5;

    public static final int upgradeBaseDamage = 2;

    public Kunai() {
        super(ID, CardCost.C1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = actualBaseDamage;
        this.magicNumber = this.baseMagicNumber = 5;
        this.returnToHand = true;
    }

    // 玩家回合结束重置伤害
    @Override
    public void triggerOnEndOfPlayerTurn() {
        baseDamage = actualBaseDamage + (upgraded ? upgradeBaseDamage : 0);
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(upgradeBaseDamage);
            upgradeMagicNumber(2);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageType.NORMAL), AttackEffect.SLASH_DIAGONAL));
        baseDamage += magicNumber;
    }
}
