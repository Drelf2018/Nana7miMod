package nana7mimod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import nana7mimod.helpers.ModHelper;

public class Cube extends Base {
    public static final String ID = ModHelper.id(Cube.class);

    public Cube() {
        super(ID, CardCost.C2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 15;
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
        addToBot(new VFXAction(new BorderFlashEffect(Color.SKY)));
        if (Settings.FAST_MODE) {
            addToBot(new VFXAction(new SmallLaserEffect(m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY), 0.1F));
        } else {
            addToBot(new VFXAction(new SmallLaserEffect(m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY), 0.3F));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageType.HP_LOSS), AttackEffect.NONE));
    }
}
