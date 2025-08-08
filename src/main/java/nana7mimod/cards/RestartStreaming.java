package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConservePower;
import nana7mimod.helpers.ModHelper;

public class RestartStreaming extends Base {
    public static final String ID = ModHelper.id(RestartStreaming.class);

    public RestartStreaming() {
        super(ID, CardCost.C2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(this.cost - 1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard c : p.hand.group) {
            if (!c.isEthereal && c != this)
                c.retain = true;
        }
        addToBot(new ApplyPowerAction(p, p, new ConservePower(p, 1)));
        addToBot(new PressEndTurnButtonAction());
    }
}
