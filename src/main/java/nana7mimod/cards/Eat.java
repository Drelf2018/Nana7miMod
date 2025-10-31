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
import nana7mimod.helpers.CharacterHelper;
import nana7mimod.helpers.ModHelper;

public class Eat extends Base {
    public static final String ID = ModHelper.id(Eat.class);

    public Eat() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 7;
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(3);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.currentHealth <= magicNumber) {
            playSound(ID);
            addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, CharacterHelper.NANA7MI_BLUE.cpy()), 0.3F));
            addToBot(new HealAction(p, p, m.currentHealth));
            addToBot(new InstantKillAction(m));
        } else {
            String extended = strings(ID).EXTENDED_DESCRIPTION[0];
            AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, extended, true));
        }
    }
}
