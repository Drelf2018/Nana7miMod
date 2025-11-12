package nana7mimod.actions;

import java.util.UUID;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.core.Settings;

public class SmokeAction extends AbstractGameAction {
    public SmokeAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    public static CardGroup getActualPurgeableCards(CardGroup cardGroup) {
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : cardGroup.group) {
            if (c.inBottleFlame || c.inBottleLightning || c.inBottleTornado)
                continue;
            if (c.cardID.equals("Necronomicurse"))
                continue;
            if (c.cardID.equals("CurseOfTheBell"))
                continue;
            if (c.cardID.equals("AscendersBane"))
                continue;
            retVal.group.add(c);
        }
        return retVal;
    }

    public static AbstractCard getActualCard(UUID uuid) {
        for (AbstractCard c : AbstractDungeon.player.hand.group)
            if (c.uuid.equals(uuid)) {
                AbstractDungeon.player.hand.removeCard(c);
                return c;
            }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group)
            if (c.uuid.equals(uuid)) {
                AbstractDungeon.player.drawPile.removeCard(c);
                return c;
            }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group)
            if (c.uuid.equals(uuid)) {
                AbstractDungeon.player.discardPile.removeCard(c);
                return c;
            }
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group)
            if (c.uuid.equals(uuid)) {
                AbstractDungeon.player.exhaustPile.removeCard(c);
                return c;
            }
        return null;
    }

    public void update() {
        if (duration == startDuration) {
            CardGroup cardGroup = SmokeAction.getActualPurgeableCards(AbstractDungeon.player.masterDeck);
            AbstractDungeon.gridSelectScreen.open(cardGroup, 1, ShopScreen.NAMES[13], false, false, true, true);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                AbstractCard c = getActualCard(card.uuid);
                if (c != null) {
                    c.setAngle(0);
                    PurgeCardEffect e = new PurgeCardEffect(c, c.current_x, c.current_y);
                    duration += e.duration;
                    startDuration += e.duration;
                    AbstractDungeon.topLevelEffects.add(e);
                }
                AbstractDungeon.player.masterDeck.removeCard(card);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        tickDuration();
    }
}
