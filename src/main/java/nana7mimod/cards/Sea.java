package nana7mimod.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.actions.DiscoveryAction;

public class Sea extends Base {
    public static final String ID = ModHelper.id(Sea.class);

    public Sea() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
        MultiCardPreview.add(this, new Freedom(), new Enemy(), new Hometown());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(cost - 1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscoveryAction(new Freedom(), new Enemy(), new Hometown()));
    }
}
