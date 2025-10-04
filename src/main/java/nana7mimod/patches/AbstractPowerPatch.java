package nana7mimod.patches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.powers.AbstractPower;
import nana7mimod.helpers.ModHelper;

@SpirePatch(clz = AbstractPower.class, method = "initialize")
public class AbstractPowerPatch {
    public static TextureAtlas powerAtlas;

    @SpireInsertPatch(rloc = 0)
    public static void Insert() {
        powerAtlas = new TextureAtlas(Gdx.files.internal(ModHelper.RESOURCES + "/image/powers/powers.atlas"));
    }

    public static void loadRegion(AbstractPower p, String fileName) {
        p.region48 = powerAtlas.findRegion("48/" + fileName);
        p.region128 = powerAtlas.findRegion("128/" + fileName);
    }
}
