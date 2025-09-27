package nana7mimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import nana7mimod.stances.KyojinStance;

@SpirePatch(clz = AbstractPlayer.class, method = "damage")
public class KyojinPatch {
    @SpireInsertPatch(loc = 1851)
    public static SpireReturn<Void> Insert() {
        AbstractPlayer p = AbstractDungeon.player;
        if (!p.hasRelic("Mark of the Bloom") && p.stance.ID == KyojinStance.STANCE_ID) {
            AbstractDungeon.actionManager.addToTop(new ChangeStanceAction("Neutral"));
            return SpireReturn.Return();
        } else {
            return SpireReturn.Continue();
        }
    }
}
