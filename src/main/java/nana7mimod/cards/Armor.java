package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;

public class Armor extends Base {
    public static final String ID = ModHelper.id(Armor.class);

    public Armor() {
        super(ID, CardCost.C1, CardType.SKILL, CardTarget.SELF);
        this.block = this.baseBlock = 4;
    }

    public void setX(int amount) {
        this.magicNumber = this.baseMagicNumber = amount;
        this.rawDescription = strings(ID).EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();
        card.baseMagicNumber = baseMagicNumber;
        card.magicNumber = magicNumber;
        card.description = description;
        return card;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            addToBot(new GainBlockAction(p, p, block));
    }
}
