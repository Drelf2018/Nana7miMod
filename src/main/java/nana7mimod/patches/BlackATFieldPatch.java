package nana7mimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.select.BossRelicSelectScreen;
import nana7mimod.relics.ATField;
import nana7mimod.relics.BlackATField;

public class BlackATFieldPatch {
    @SpirePatch(clz = BossRelicSelectScreen.class, method = "relicObtainLogic")
    public static class BossRelicSelectScreenPatch {
        @SpireInsertPatch(loc = 251)
        public static void Insert(BossRelicSelectScreen __instance, AbstractRelic r) {
            if (r.relicId.equals(BlackATField.ID)) {
                r.counter = AbstractDungeon.player.getRelic(ATField.ID).counter;
                r.instantObtain(AbstractDungeon.player, 0, true);
                AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
            }
        }
    }

    @SpirePatch(clz = AbstractRelic.class, method = "bossObtainLogic")
    public static class AbstractRelicPatch {
        @SpireInsertPatch(loc = 440)
        public static SpireReturn<Void> Insert(AbstractRelic __instance) {
            if (__instance.relicId.equals(BlackATField.ID)) {
                __instance.isObtained = true;
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}
