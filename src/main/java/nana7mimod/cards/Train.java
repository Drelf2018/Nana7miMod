package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import nana7mimod.helpers.ModHelper;

public class Train extends Base {
    public static final String ID = ModHelper.id(Train.class);

    public Train() {
        super(ID, CardCost.C0, CardType.ATTACK, CardTarget.NONE);
    }

    public Train(CardTarget target) {
        super(ID + target.ordinal(), CardCost.C0, CardType.ATTACK, target);
        this.isMultiDamage = target == CardTarget.ALL_ENEMY;
        this.damage = this.baseDamage = this.isMultiDamage ? 7 : 10;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(5);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isMultiDamage) {
            addToBot(new SFXAction("ATTACK_HEAVY"));
            addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
            addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageType.NORMAL, AttackEffect.NONE));
        } else
            addToBot(new DamageAction(m, new DamageInfo(p, damage), AttackEffect.SLASH_DIAGONAL));
    }
}
