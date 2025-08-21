package nana7mimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import nana7mimod.characters.Nana7mi;

public class IdolAction extends AbstractGameAction {
    public IdolAction() {
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p instanceof Nana7mi) {
            ((Nana7mi) p).changeStand("stand-idol.png");
        }
        isDone = true;
    }
}
