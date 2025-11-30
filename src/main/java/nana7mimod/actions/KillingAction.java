package nana7mimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import nana7mimod.powers.ATFieldPower;
import nana7mimod.relics.ATField;
import nana7mimod.relics.BlackATField;

public class KillingAction extends AbstractGameAction {
    private DamageInfo info;

    public KillingAction(AbstractCreature target, DamageInfo info, int amount) {
        this.info = info;
        setValues(target, info);
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = 0.1F;
    }

    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_HORIZONTAL));
            this.target.damage(this.info);
            if ((((AbstractMonster) this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead
                    && !this.target.hasPower("Minion")) {
                AbstractRelic r = AbstractDungeon.player.getRelic(ATField.ID);
                if (r == null)
                    r = AbstractDungeon.player.getRelic(BlackATField.ID);
                if (r != null) {
                    r.counter += amount;
                    r.updateDescription(null);
                }
                ATFieldPower.addAmount(AbstractDungeon.player, amount);
            }
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
        }
        tickDuration();
    }
}
