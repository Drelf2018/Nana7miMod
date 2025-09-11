package nana7mimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;

@SpirePatch(clz = GameActionManager.class, method = "getNextAction")
public class GetNextActionPatch {
    @SpireInsertPatch(loc = 456)
    public static void Insert() {
        ClearPatch.damageReceivedLastTurn = GameActionManager.damageReceivedThisTurn;
    }
}
