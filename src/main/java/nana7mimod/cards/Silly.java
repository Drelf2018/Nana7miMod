package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import nana7mimod.helpers.ModHelper;

public class Silly extends Base {
    public static final String ID = ModHelper.id(Silly.class);

    public Silly() {
        super(ID, CardCost.C0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 3;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(ConfusionPower.POWER_ID))
            addToBot(new ApplyPowerAction(p, p, new ConfusionPower(p) {
                @Override
                public void atEndOfTurn(boolean isPlayer) {
                    if (isPlayer)
                        addToBot(new ReducePowerAction(owner, owner, this, 1));
                }
            }));
        addToBot(new DrawCardAction(p, magicNumber));
    }
}
