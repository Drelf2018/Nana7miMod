package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;

public class Defend extends Base {
    public static final String ID = ModHelper.id(Defend.class);

    public Defend() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.block = this.baseBlock = 4;
        this.tags.add(CardTags.STARTER_DEFEND);
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }
}
