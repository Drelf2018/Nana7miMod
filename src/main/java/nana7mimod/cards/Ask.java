package nana7mimod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.actions.DiscoveryGameAction;
import nana7mimod.helpers.ModHelper;

public class Ask extends Base {
    public static final String ID = ModHelper.id(Ask.class);

    public Ask() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.cardsToPreview = new Game();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscoveryGameAction(1));
        if (upgraded) {
            addToBot(new DiscoveryGameAction(2));
        }
    }
}
