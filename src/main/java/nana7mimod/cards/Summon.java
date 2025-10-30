package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.ATFieldPower;

public class Summon extends Base {
    public static final String ID = ModHelper.id(Summon.class);

    public Summon() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ATFieldPower power = ATFieldPower.from(p);
        int extraDraws = power != null && power.amount > 0 ? power.amount : 0;
        addToBot(new DrawCardAction(p, magicNumber + Math.min(extraDraws, 3)));
    }
}
