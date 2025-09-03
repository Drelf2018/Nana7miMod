package nana7mimod.actions;

import java.util.ArrayList;
import java.util.Arrays;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;

public class DiscoveryAction extends AbstractGameAction {
    private ArrayList<AbstractCard> cards;

    public DiscoveryAction(AbstractCard... cards) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.cards = new ArrayList<>(Arrays.asList(cards));
    }

    public void update() {
        if (duration == startDuration) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(cards, CardRewardScreen.TEXT[1], true);
            tickDuration();
            return;
        }
        if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
            addToBot(new AddCardAction(AbstractDungeon.cardRewardScreen.discoveryCard));
            AbstractDungeon.cardRewardScreen.discoveryCard = null;
        }
        isDone = true;
    }
}
