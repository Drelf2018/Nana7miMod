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
    public static Music Haruhikage = MainMusic.newMusic(ModHelper.music("Haruhikage"));
    public static Music Sea = MainMusic.newMusic(ModHelper.music("Sea"));

    @SpireInsertPatch(rloc = 0)
    public static SpireReturn<Music> Insert(TempMusic __instance, String key) {
        switch (key) {
            case "HARUHIKAGE":
                return SpireReturn.Return(Haruhikage);
            case "SEA":
                return SpireReturn.Return(Sea);
            default:
                return SpireReturn.Continue();
        }
    }
}
