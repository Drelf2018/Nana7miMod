package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;

public class Kunai extends Base {
    public static final String ID = ModHelper.id(Kunai.class);

    public static final int ACTUAL_BASE_DAMAGE = 5;

    public static final int UPGRADE_BASE_DAMAGE = 2;

    public static final String DESCRIPTION = strings(ID).DESCRIPTION;

    public static final String[] EXTENDED = strings(ID).EXTENDED_DESCRIPTION;

    public Kunai() {
        super(ID, CardCost.C1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = ACTUAL_BASE_DAMAGE;
        this.magicNumber = this.baseMagicNumber = 5;
        this.returnToHand = true;
    }

    // 玩家回合结束重置伤害
    @Override
    public void triggerOnEndOfPlayerTurn() {
        baseDamage = ACTUAL_BASE_DAMAGE + (upgraded ? UPGRADE_BASE_DAMAGE : 0);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        rawDescription = DESCRIPTION + EXTENDED[0] + timesCardPlayedThisTurn() + EXTENDED[1];
        initializeDescription();
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
        returnToHand = timesCardPlayedThisTurn() < 10;
        if (!returnToHand)
            applyPowers();
    }
}
