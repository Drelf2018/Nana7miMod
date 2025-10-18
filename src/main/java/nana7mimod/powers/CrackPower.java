package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import nana7mimod.helpers.ModHelper;

public class CrackPower extends Base {
    public static final String POWER_ID = ModHelper.id(CrackPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CrackPower(AbstractCreature owner, int amount) {
        super(POWER_ID, powerStrings.NAME, owner, amount, PowerType.BUFF);

        for (AbstractCard c : AbstractDungeon.player.hand.group)
            if (c.type == CardType.ATTACK)
                c.modifyCostForCombat(-9);
        for (AbstractCard c : AbstractDungeon.player.drawPile.group)
            if (c.type == CardType.ATTACK)
                c.modifyCostForCombat(-9);
        for (AbstractCard c : AbstractDungeon.player.discardPile.group)
            if (c.type == CardType.ATTACK)
                c.modifyCostForCombat(-9);
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group)
            if (c.type == CardType.ATTACK)
                c.modifyCostForCombat(-9);
    }

    public void onCardDraw(AbstractCard card) {
        if (card.type == CardType.ATTACK)
            card.setCostForTurn(-9);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == CardType.ATTACK) {
            flashWithoutSound();
            action.exhaustCard = true;
            ATFieldPower.addAmount(owner, amount);
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
