package nana7mimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.Settings;

public class ExhaustAllAction extends AbstractGameAction {
    private CardGroup cardGroup;
    private CardGroup exhaustGroup;

    public ExhaustAllAction(CardGroup cardGroup, CardType cardType) {
        this.cardGroup = cardGroup;
        this.exhaustGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : cardGroup.group)
            if (cardType == null || cardType == card.type)
                this.exhaustGroup.addToTop(card);
        this.actionType = ActionType.WAIT;
        this.duration = this.startDuration = this.exhaustGroup.group.size() * Settings.ACTION_DUR_FAST;
    }

    public ExhaustAllAction(CardGroup cardGroup) {
        this(cardGroup, null);
    }

    public void update() {
        if (this.duration == this.startDuration) {
            for (AbstractCard card : this.exhaustGroup.group)
                addToTop(new ExhaustSpecificCardAction(card, this.cardGroup));
        }
        this.isDone = true;
    }
}
