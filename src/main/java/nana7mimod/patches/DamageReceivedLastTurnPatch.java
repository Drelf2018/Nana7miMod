package nana7mimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;

public class DamageReceivedLastTurnPatch {
    public static int damageReceivedLastTurn = 0;

    @SpirePatch(clz = GameActionManager.class, method = "clear")
    public static class ClearPatch {
        @SpireInsertPatch(rloc = 0)
        public static void Insert() {
            damageReceivedLastTurn = 0;
        }
    }

    @SpirePatch(clz = GameActionManager.class, method = "getNextAction")
    public static class GetNextActionPatch {
        @SpireInsertPatch(loc = 456)
        public static void Insert() {
            DamageReceivedLastTurnPatch.damageReceivedLastTurn = GameActionManager.damageReceivedThisTurn;
        }
    }
}
