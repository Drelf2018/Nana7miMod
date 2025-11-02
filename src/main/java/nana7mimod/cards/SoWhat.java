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
        this.damage = this.baseDamage = 3;
        this.magicNumber = this.baseMagicNumber = 50;
        this.baseBlock = 0; // 用格挡值存储已打出次数
    }

    // 玩家回合结束重置已打出次数
    @Override
    public void triggerOnEndOfPlayerTurn() {
        baseBlock = 0;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(25);
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playSound(ID);
        returnToHand = AbstractDungeon.cardRandomRng.random(99) >= Math.min(100, (upgraded ? 2 : 40) + baseBlock * 13);
        exhaust = !returnToHand;
        baseBlock += 1;
        addToBot(new DamageAction(m, new DamageInfo(p, damage), AttackEffect.BLUNT_LIGHT));
    }
}
