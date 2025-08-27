package nana7mimod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import nana7mimod.helpers.CharacterHelper.Nana7mi;
import nana7mimod.effects.ClawEffectMirror;
import nana7mimod.helpers.ModHelper;

public class Scratch extends Base {
    public static final String ID = ModHelper.id(Scratch.class);

    public Scratch() {
        super(ID, CardCost.C1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 3;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(-2);
            upgradeMagicNumber(4);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber / 2; ++i) {
            addToBot(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Nana7mi.COLOR, Color.WHITE), 0.1F));
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageType.NORMAL), AttackEffect.NONE));
            addToBot(new VFXAction(new ClawEffectMirror(m.hb.cX, m.hb.cY, Nana7mi.COLOR, Color.WHITE), 0.1F));
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageType.NORMAL), AttackEffect.NONE));
        }
    }
}
