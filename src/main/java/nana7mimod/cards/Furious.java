package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.ATFieldPower;
import com.megacrit.cardcrawl.core.Settings;

public class Furious extends Base {
    public static final String ID = ModHelper.id(Furious.class);

    public Furious() {
        super(ID, CardCost.C2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(cost - 1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                int exhaustCount = p.hand.size();
                if (exhaustCount > 0)
                    addToTop(new ApplyPowerAction(p, p, new ATFieldPower(p, exhaustCount * magicNumber)));
                for (int i = 0; i < exhaustCount; ++i) {
                    if (Settings.FAST_MODE)
                        addToTop((AbstractGameAction) new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));
                    else
                        addToTop((AbstractGameAction) new ExhaustAction(1, true, true));
                }
                isDone = true;
            }
        });
    }
}
