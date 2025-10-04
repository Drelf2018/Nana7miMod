package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import nana7mimod.helpers.ModHelper;
import nana7mimod.patches.AbstractPowerPatch;

public class UnnPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.id(UnnPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public UnnPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        updateDescription();
        AbstractPowerPatch.loadRegion(this, "unn");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            boolean playedAttackCard = false;
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn)
                if (c.type == CardType.ATTACK) {
                    playedAttackCard = true;
                    break;
                }

            if (!playedAttackCard) {
                flashWithoutSound();
                addToTop(new HealAction(owner, owner, amount));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
