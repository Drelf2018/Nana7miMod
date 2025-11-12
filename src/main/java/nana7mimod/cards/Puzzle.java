package nana7mimod.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.actions.DiscoveryAction;
import nana7mimod.helpers.ModHelper;

public class Puzzle extends Base {
    public static final String ID = ModHelper.id(Puzzle.class);

    public static final String DESCRIPTION = strings(ID).DESCRIPTION;

    public static final String UPGRADE = strings(ID).UPGRADE_DESCRIPTION;

    public static final String[] EXTENDED = strings(ID).EXTENDED_DESCRIPTION;

    public Puzzle() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.isEthereal = true;
        this.returnToHand = true;
        MultiCardPreview.add(this, new Train(CardTarget.ENEMY), new Train(CardTarget.ALL_ENEMY));
    }

    public void upgrade() {
        if (!upgraded) {
            this.isEthereal = false;
            upgradeName();
            upgradeDescription();
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        rawDescription = upgraded ? UPGRADE : DESCRIPTION + EXTENDED[0] + timesCardPlayedThisTurn() + EXTENDED[1];
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playSound(ID);
        addToBot(new DiscoveryAction(new Train(CardTarget.ENEMY), new Train(CardTarget.ALL_ENEMY)));
        returnToHand = timesCardPlayedThisTurn() < 10;
        if (!returnToHand)
            applyPowers();
    }
}
