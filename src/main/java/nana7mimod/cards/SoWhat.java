package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;

public class SoWhat extends Base {
    public static final String ID = ModHelper.id(SoWhat.class);

    public static final String DESCRIPTION = strings(ID).DESCRIPTION;

    public static final String UPGRADE = strings(ID).UPGRADE_DESCRIPTION;

    public static final String[] EXTENDED = strings(ID).EXTENDED_DESCRIPTION;

    public SoWhat() {
        super(ID, CardCost.C0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 3;
        this.magicNumber = this.baseMagicNumber = 50;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(25);
            upgradeDescription();
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        rawDescription = (upgraded ? UPGRADE : DESCRIPTION) + EXTENDED[0] + timesCardPlayedThisTurn() + EXTENDED[1];
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playSound(ID);
        int played = timesCardPlayedThisTurn();
        returnToHand = AbstractDungeon.cardRandomRng.random(99) >= Math.min(100, (upgraded ? 2 : 40) + (played - 1) * 13) && played < 10;
        exhaust = !returnToHand;
        if (!returnToHand)
            applyPowers();
        addToBot(new DamageAction(m, new DamageInfo(p, damage), AttackEffect.BLUNT_LIGHT));
    }
}
