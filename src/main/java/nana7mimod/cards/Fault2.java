package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;

public class Fault2 extends Base {
    public static final String ID = ModHelper.id(Fault2.class);

    public Fault2() {
        super(ID, CardCost.C2, CardType.ATTACK, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage = 20;
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
        this.isMultiDamage = true;
        this.cardsToPreview = new Fault3();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(cost - 1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            addToBot(new DamageAction(mo, new DamageInfo(p, damage, DamageType.NORMAL), AttackEffect.SLASH_HORIZONTAL));
            addToBot(new LoseHPAction(p, p, magicNumber));
        }
        addToBot(new MakeTempCardInHandAction(new Fault3(), 1));
    }
}
