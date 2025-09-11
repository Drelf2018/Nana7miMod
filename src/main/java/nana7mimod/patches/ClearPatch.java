package nana7mimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;

@SpirePatch(clz = GameActionManager.class, method = "clear")
public class ClearPatch {
    public static int damageReceivedLastTurn = 0;

    @SpireInsertPatch(rloc = 0)
    public static void Insert() {
        damageReceivedLastTurn = 0;
    }
}
