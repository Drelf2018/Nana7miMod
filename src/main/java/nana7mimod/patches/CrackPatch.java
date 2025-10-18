package nana7mimod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import nana7mimod.powers.CrackPower;

public class CrackPatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "releaseCard")
    public static class ReleaseCardPatch {
        @SpireInsertPatch(loc = 1668)
        public static void Insert() {
            if (AbstractDungeon.player.hasPower(CrackPower.POWER_ID))
                for (AbstractCard c : AbstractDungeon.player.hand.group)
                    if (c.type == CardType.ATTACK && c.costForTurn != 0)
                        c.modifyCostForCombat(-9);
        }
    }

    @SpirePatch(clz = CardGroup.class, method = "addToHand")
    public static class AddToHandPatch {
        @SpireInsertPatch(rloc = 0)
        public static void Insert(CardGroup __instance, AbstractCard c) {
            if (AbstractDungeon.player.hasPower(CrackPower.POWER_ID) && c.type == CardType.ATTACK)
                c.setCostForTurn(-9);
        }
    }
}
