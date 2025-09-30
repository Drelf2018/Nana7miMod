package nana7mimod.patches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import nana7mimod.helpers.ModHelper;

public class ImageMasterPatch {
    public static TextureAtlas vfxAtlas;

    public static TextureAtlas.AtlasRegion ATK_BLACK_FIRE;

    @SpirePatch(clz = ImageMaster.class, method = "initialize")
    public static class InitializePatch {
        @SpireInsertPatch(rloc = 0)
        public static void Insert() {
            vfxAtlas = new TextureAtlas(Gdx.files.internal(ModHelper.RESOURCES + "/image/vfx/vfx.atlas"));
            ATK_BLACK_FIRE = vfxAtlas.findRegion("attack/black_fire");
        }
    }
}
