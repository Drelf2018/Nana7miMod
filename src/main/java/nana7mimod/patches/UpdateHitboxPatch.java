package nana7mimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import nana7mimod.characters.Nana7mi;

@SpirePatch(clz = CharacterOption.class, method = "updateHitbox")
public class UpdateHitboxPatch {
    @SpireInsertPatch(loc = 195)
    public static void Insert(CharacterOption __instance) {
        // 播放无垠梦海
        if (__instance.c.chosenClass == Nana7mi.PlayerColorEnum.NANA7MI)
            CardCrawlGame.music.playTempBgmInstantly("SEA");
        else
            CardCrawlGame.music.silenceTempBgmInstantly();
    }
}
