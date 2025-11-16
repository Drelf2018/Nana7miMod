package nana7mimod.patches;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.neow.NeowEvent;
import nana7mimod.helpers.ModHelper;
import nana7mimod.modcore.Nana7miMod;
import nana7mimod.relics.Apple;
import nana7mimod.characters.Nana7mi.PlayerColorEnum;

public class NeowEventPatch {
    @SpirePatch(clz = NeowEvent.class, method = "playSfx")
    public static class PlaySfxPatch {
        @SpireInsertPatch(rloc = 0)
        public static void Insert() {
            CardCrawlGame.music.fadeOutTempBGM();
        }
    }

    @SpirePatch(clz = NeowEvent.class, method = "render")
    public static class RenderPatch {
        public static Texture Nana7miFans = ImageMaster.loadImage(ModHelper.RESOURCES + "/image/nana7miFans.png");

        @SpireInsertPatch(rloc = 1)
        public static void Insert(NeowEvent __instance, SpriteBatch sb) {
            if (AbstractDungeon.player.chosenClass.equals(PlayerColorEnum.NANA7MI))
                sb.draw(Nana7miFans, 0.7F * Settings.WIDTH, 0.1F * Settings.HEIGHT, 0.2F * Settings.WIDTH,
                        0.2F * Settings.WIDTH * Nana7miFans.getHeight() / Nana7miFans.getWidth());
        }
    }

    @SpirePatch(clz = NeowEvent.class, method = "buttonEffect")
    public static class ButtonEffectPatch {
        @SpireInsertPatch(rloc = 0)
        public static void Insert(NeowEvent __instance, int buttonPressed) {
            if (Nana7miMod.StoryMode && !AbstractDungeon.player.hasRelic(Apple.ID)) {
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, new Apple());
            }
        }
    }
}
