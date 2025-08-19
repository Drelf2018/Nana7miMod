package nana7mimod.actions;

import java.util.ArrayList;
import java.util.Collections;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.QuestionCard;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import nana7mimod.cards.Game;
import nana7mimod.relics.ATField;
import java.util.Random;

public class DiscoveryGameAction extends AbstractGameAction {
    private int total;
    private int incorrect;

    public DiscoveryGameAction(int total, int incorrect) {
        this.total = total;
        this.incorrect = incorrect;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    public DiscoveryGameAction(int incorrect) {
        this(3, incorrect);
    }

    public DiscoveryGameAction() {
        this(3, 1);
    }

    public void update() {
        if (duration == startDuration) {
            ArrayList<AbstractCard> generatedCards = generateCardChoices();
            AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1], true);
            tickDuration();
            return;
        }
        if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
            AbstractDungeon.player.useCard(AbstractDungeon.cardRewardScreen.discoveryCard, null, 0);
            AbstractDungeon.cardRewardScreen.discoveryCard = null;
        }
        isDone = true;
    }

    private ArrayList<AbstractCard> generateCardChoices() {
        ArrayList<AbstractCard> derp = new ArrayList<AbstractCard>();
        if (AbstractDungeon.player.hasRelic(QuestionCard.ID)) {
            total++;
        }
        Random random = new Random();
        for (int i = 0; i < incorrect; i++) {
            derp.add(Game.Incorrect(random.nextInt(Game.INCORRECT)));
        }
        if (ATField.getFirstTimePlayGame()) {
            derp.add(Game.Correct(65));
        }
        while (derp.size() < total) {
            derp.add(Game.Correct(random.nextInt(Game.CORRECT)));
        }
        Collections.shuffle(derp);
        return derp;
    }
}
