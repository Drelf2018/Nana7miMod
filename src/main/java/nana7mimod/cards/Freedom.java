package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;

public class Freedom extends Base {
    public static final String ID = ModHelper.id(Freedom.class);

    public Freedom() {
        super(ID, CardCost.C1, CardType.SKILL, CardTarget.SELF);
        this.block = this.baseBlock = 15;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(5);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }
}
