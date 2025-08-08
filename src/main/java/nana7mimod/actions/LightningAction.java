package nana7mimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class LightningAction extends AbstractGameAction {
    private DamageInfo info;

    public LightningAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        this.target = target;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.NONE;
    }

    public void update() {
        this.addToTop(new DamageAction(target, info, AttackEffect.NONE, true));
        this.addToTop(new VFXAction(new LightningEffect(target.drawX, target.drawY), Settings.FAST_MODE ? 0.0F : 0.2F));
        this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE"));
        this.isDone = true;
    }
}
