package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.ATFieldPower;

public class Axe extends Base {
    public static final String ID = ModHelper.id(Axe.class);

    public Axe() {
        super(ID, CardCost.C2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(cost - 1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int discardCount = 0;
        for (AbstractCard c : p.hand.group) {
            if (c.type == CardType.ATTACK) {
                addToBot(new DiscardSpecificCardAction(c, p.hand));
                ++discardCount;
            }
        }
        if (discardCount != 0) {
            addToBot(new ApplyPowerAction(p, p, new ATFieldPower(p, discardCount * magicNumber)));
        }
    }
}
