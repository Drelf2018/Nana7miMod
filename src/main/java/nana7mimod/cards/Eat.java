package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import nana7mimod.helpers.CharacterHelper.Nana7mi;
import nana7mimod.helpers.ModHelper;

public class Eat extends Base {
    public static final String ID = ModHelper.id(Eat.class);

    public Eat() {
        super(ID, CardCost.C1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.damage = this.baseDamage = 8;
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(4);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.currentHealth <= damage) {
            addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Nana7mi.COLOR.cpy()), 0.3F));
            addToBot(new HealAction(p, p, m.currentHealth));
            addToBot(new InstantKillAction(m));
        } else {
            String ext = strings(ID).EXTENDED_DESCRIPTION[0];
            AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, ext, true));
        }
    }
}
