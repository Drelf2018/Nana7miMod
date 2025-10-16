package nana7mimod.patches;

import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.audio.TempMusic;
import nana7mimod.helpers.ModHelper;

@SpirePatch(clz = TempMusic.class, method = "getSong")
public class TempMusicPatch {
    public static Music SEA = MainMusic.newMusic(ModHelper.audio("sea.mp3"));

    @SpireInsertPatch(rloc = 0)
    public static SpireReturn<Music> Insert(TempMusic __instance, String key) {
        switch (key) {
            case "SEA":
                return SpireReturn.Return(SEA);
        }
        return SpireReturn.Continue();
    }
}
