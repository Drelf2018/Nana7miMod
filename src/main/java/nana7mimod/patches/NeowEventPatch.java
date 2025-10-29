package nana7mimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.neow.NeowEvent;

public class NeowEventPatch {
    @SpirePatch(clz = NeowEvent.class, method = "playSfx")
    public static class PlaySfxPatch {
        @SpireInsertPatch(rloc = 0)
        public static void Insert() {
            CardCrawlGame.music.fadeOutTempBGM();
        }
    }
}
