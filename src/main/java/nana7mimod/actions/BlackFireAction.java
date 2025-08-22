package nana7mimod.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import nana7mimod.helpers.ModHelper;

public class BlackFireAction extends DamageAction {
    public static final TextureAtlas vfxAtlas = new TextureAtlas(Gdx.files.internal(ModHelper.RESOURCES + "/image/vfx/vfx.atlas"));

    private DamageInfo info;

    public BlackFireAction(AbstractCreature target, DamageInfo info) {
        super(target, info, AttackEffect.FIRE);
        this.info = info;
    }

    @Override
    public void update() {
        if (this.shouldCancelAction() && this.info.type != DamageType.THORNS) {
            this.isDone = true;
        } else {
            if (this.duration == 0.1F) {
                if (this.info.type != DamageType.THORNS && (this.info.owner.isDying || this.info.owner.halfDead)) {
                    this.isDone = true;
                    return;
                }

                FlashAtkImgEffect effect = new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.FIRE, false);
                effect.img = vfxAtlas.findRegion("attack/black_fire");
                AbstractDungeon.effectList.add(effect);
            }

            this.tickDuration();
            if (this.isDone) {
                this.target.tint.color.set(Color.RED);
                this.target.tint.changeColor(Color.WHITE.cpy());

                this.target.damage(this.info);
                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                }

                if (!Settings.FAST_MODE) {
                    this.addToTop(new WaitAction(0.1F));
                }
            }

        }
    }
}
