package nana7mimod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.actions.DiscoveryTrainAction;
import nana7mimod.helpers.ModHelper;

public class Puzzle extends Base {
    public static final String ID = ModHelper.id(Puzzle.class);

    public Puzzle() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.isEthereal = true;
        this.returnToHand = true;
        this.cardsToPreview = new Train();
    }

    public void upgrade() {
        if (!upgraded) {
            this.isEthereal = false;
            upgradeName();
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscoveryTrainAction());
    }
}
