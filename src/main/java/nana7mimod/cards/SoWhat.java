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

    public SoWhat() {
        super(ID, CardCost.C0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 6;
        this.magicNumber = this.baseMagicNumber = 50;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(-2);
            upgradeMagicNumber(25);
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playSound(ID);
        returnToHand = AbstractDungeon.cardRandomRng.random(99) < magicNumber;
        exhaust = !returnToHand;
        addToBot(new DamageAction(m, new DamageInfo(p, damage), AttackEffect.BLUNT_LIGHT));
    }
}
