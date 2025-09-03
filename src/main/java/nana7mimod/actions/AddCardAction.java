package nana7mimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

public class AddCardAction extends AbstractGameAction {
    private AbstractCard card;
    private float offsetX;
    private float offsetY;

    public AddCardAction(AbstractCard card, float offsetX, float offsetY) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.card = card;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public AddCardAction(AbstractCard card) {
        this(card, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F);
    }

    public void update() {
        if (AbstractDungeon.player.hand.size() < 10)
            AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(card, offsetX, offsetY));
        else
            AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(card, offsetX, offsetY));
        isDone = true;
    }
}
