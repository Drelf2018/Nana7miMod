package nana7mimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class WaitAction extends AbstractGameAction {
    public WaitAction(float setDur) {
        this.duration = setDur;
        this.actionType = ActionType.WAIT;
    }

    public void update() {
        tickDuration();
    }
}
