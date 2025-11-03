package nana7mimod.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import nana7mimod.stances.KyojinStance;

public class KyojinPatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "damage")
    public static class DamagePatch {
        @SpireInsertPatch(loc = 1851)
        public static SpireReturn<Void> Insert(AbstractPlayer __instance, DamageInfo info) {
            if (!__instance.hasRelic("Mark of the Bloom") && __instance.stance.ID == KyojinStance.STANCE_ID) {
                AbstractDungeon.actionManager.addToTop(new ChangeStanceAction("Neutral"));
                return SpireReturn.Return();
            } else {
                return SpireReturn.Continue();
            }
        }
    }

    @SpirePatch(clz = AbstractPlayer.class, method = "render")
    public static class RenderPatch {
        @SpireInsertPatch(loc = 2123)
        public static SpireReturn<Void> Insert(AbstractPlayer __instance, SpriteBatch sb) {
            float scale = __instance.stance.ID == KyojinStance.STANCE_ID ? 1.75F : 1.0F;
            sb.draw(__instance.img, __instance.drawX - __instance.img.getWidth() * Settings.scale * scale / 2.0F + __instance.animX, __instance.drawY,
                    __instance.img.getWidth() * Settings.scale * scale, __instance.img.getHeight() * Settings.scale * scale, 0, 0,
                    __instance.img.getWidth(), __instance.img.getHeight(), __instance.flipHorizontal, __instance.flipVertical);
            __instance.hb.render(sb);
            __instance.healthHb.render(sb);
            return SpireReturn.Return();
        }
    }
}
