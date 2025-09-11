package nana7mimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;

@SpirePatch(clz = AbstractDungeon.class, method = "nextRoomTransition", paramtypez = {SaveFile.class})
public class OnEnterRoomPatch {
    public interface ClothingHandler {
        void PutOnClothes();

        void TakeOffClothes();
    }

    @SpireInsertPatch(loc = 2215)
    public static void Insert() {
        if (AbstractDungeon.player instanceof ClothingHandler)
            ((ClothingHandler) AbstractDungeon.player).TakeOffClothes();
    }
}
