package nana7mimod.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.actions.DiscoveryAction;
import nana7mimod.helpers.ModHelper;

public class Puzzle extends Base {
    public static final String ID = ModHelper.id(Puzzle.class);

    public Puzzle() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.isEthereal = true;
        this.returnToHand = true;
        MultiCardPreview.add(this, new Train(false), new Train(true));
    }

    public void upgrade() {
        if (!upgraded) {
            this.isEthereal = false;
            upgradeName();
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscoveryAction(new Train(false), new Train(true)));
    }
}
