package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import nana7mimod.helpers.ModHelper;
import nana7mimod.actions.ExhaustAllAction;

public class Fault3 extends Base {
    public static final String ID = ModHelper.id(Fault3.class);

    public Fault3() {
        super(ID, CardCost.C2, CardType.ATTACK, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage = 80;
        this.magicNumber = this.baseMagicNumber = 8;
        this.exhaust = true;
        this.isEthereal = true;
        this.isMultiDamage = true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(cost - 1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster n) {
        addToBot(new LoseHPAction(p, p, magicNumber));
        addToBot(new ExhaustAllAction(p.hand));
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters)
            addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        addToBot(new WaitAction(0.8F));
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AttackEffect.NONE));
    }
}
