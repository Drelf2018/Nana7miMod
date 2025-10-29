package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;

public class GloomShroom extends Base {
    public static final String ID = ModHelper.id(GloomShroom.class);

    public GloomShroom() {
        super(ID, CardCost.C0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 1;
        this.magicNumber = this.baseMagicNumber = 6;
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; ++i) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage), AttackEffect.SLASH_HORIZONTAL));
        }
    }
}
