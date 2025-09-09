package nana7mimod.helpers;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;

@SpirePatch(clz = GameActionManager.class, method = "getNextAction")
public class GameActionManagerPatch {
    public static int damageReceivedLastTurn = 0;

    @SpireInsertPatch(loc = 456)
    public static void Insert() {
        damageReceivedLastTurn = GameActionManager.damageReceivedThisTurn;
    }
}
