package nana7mimod.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import java.util.UUID;

public class CuishaLetsGOAction extends AbstractGameAction {
    private int increaseAmount;
    private UUID uuid;

    public CuishaLetsGOAction(int incAmount, UUID targetUUID) {
        this.increaseAmount = incAmount;
        this.uuid = targetUUID;
    }

    public void update() {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.uuid.equals(uuid)) {
                c.misc += increaseAmount;
                c.applyPowers();
                c.baseDamage = c.misc;
                c.isDamageModified = false;
            }
        }

        for (AbstractCard c : GetAllInBattleInstances.get(uuid)) {
            c.misc += increaseAmount;
            c.applyPowers();
            c.baseDamage = c.misc;
        }

        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }

        isDone = true;
    }
}
