package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.WrathStance;
import nana7mimod.helpers.ModHelper;

public class Sleepy extends Base {
    public static final String ID = ModHelper.id(Sleepy.class);

    public Sleepy() {
        super(ID, CardCost.C1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage = 4;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChangeStanceAction(new WrathStance() {
            @Override
            public void atStartOfTurn() {
                AbstractDungeon.actionManager.addToBottom(new ChangeStanceAction("Neutral"));
            }
        }));
        addToBot(new AttackDamageRandomEnemyAction(this, AttackEffect.BLUNT_LIGHT));
    }
}
