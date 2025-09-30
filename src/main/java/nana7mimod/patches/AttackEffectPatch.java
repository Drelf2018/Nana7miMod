package nana7mimod.patches;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class AttackEffectPatch {
    @SpireEnum
    public static AttackEffect BLACK_FIRE;

    @SpirePatch(clz = FlashAtkImgEffect.class, method = "loadImage")
    public static class LoadImagePatch {
        @SpireInsertPatch(rloc = 0)
        public static SpireReturn<TextureAtlas.AtlasRegion> Insert(FlashAtkImgEffect __instance, AttackEffect ___effect) {
            if (___effect == BLACK_FIRE) {
                return SpireReturn.Return(ImageMasterPatch.ATK_BLACK_FIRE);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = FlashAtkImgEffect.class, method = "playSound")
    public static class PlaySoundPatch {
        @SpireInsertPatch(rloc = 0)
        public static SpireReturn<Void> Insert(FlashAtkImgEffect __instance, AttackEffect effect) {
            if (effect == BLACK_FIRE) {
                CardCrawlGame.sound.play("ATTACK_FIRE");
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}
