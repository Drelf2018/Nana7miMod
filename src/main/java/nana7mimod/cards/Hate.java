package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.ATFieldPower;

public class Hate extends Base {
    public static final String ID = ModHelper.id(Hate.class);

    public Hate(int upgrades) {
        super(ID, CardCost.C1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 1;
        this.magicNumber = this.baseMagicNumber = 1;
        this.timesUpgraded = upgrades;
    }

    public Hate() {
        this(0);
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
        if (cardPlayed == this)
            ATFieldPower.addAmount(AbstractDungeon.player, magicNumber);
    }

    public void upgrade() {
        upgradeTimesUpgraded();
        upgradeDamage(timesUpgraded);
        upgradeMagicNumber(1);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage), AttackEffect.BLUNT_LIGHT));
    }
}
