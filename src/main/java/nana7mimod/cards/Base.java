package nana7mimod.cards;

import basemod.AutoAdd;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.characters.Nana7mi;
import nana7mimod.helpers.ModHelper;

@AutoAdd.Ignore
public class Base extends CustomCard {
    public enum CardCost {
        CN, CX, C0, C1, C2, C3, C4, C5, C6;
    }

    public Base(String id, CardCost cost, CardType type, CardTarget target) {
        this(id, cost, type, CardColor.COLORLESS, CardRarity.SPECIAL, target);
    }

    public Base(String id, CardCost cost, CardType type, CardRarity rarity, CardTarget target) {
        this(id, cost, type, Nana7mi.PlayerColorEnum.NANA7MI_BLUE, rarity, target);
    }

    public Base(String id, CardCost cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, getName(id), ModHelper.cards(type, id), cost.ordinal() - 2, getDesc(id), type, color, rarity, target);
    }

    public static CardStrings strings(String id) {
        return CardCrawlGame.languagePack.getCardStrings(id);
    }

    public static String getName(String id) {
        return strings(id).NAME;
    }

    public static String getDesc(String id) {
        return strings(id).DESCRIPTION;
    }

    @Override
    public void upgrade() {}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    public void upgradeDescription() {
        this.rawDescription = strings(this.cardID).UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
