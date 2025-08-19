package nana7mimod.cards;

import basemod.AutoAdd;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import nana7mimod.characters.Nana7mi;
import nana7mimod.helpers.ModHelper;

@AutoAdd.Ignore
public abstract class Base extends CustomCard {
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
        super(id, getName(id), ModHelper.cards(type, id), cost.ordinal() - 2, getDescription(id), type, color, rarity, target);
    }

    public Base(String id, String name, String img) {
        super(id, name, img, -2, getDescription(id), CardType.STATUS, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    }

    public static CardStrings strings(String id) {
        return CardCrawlGame.languagePack.getCardStrings(id);
    }

    public static String getName(String id) {
        return strings(id).NAME;
    }

    public static String getDescription(String id) {
        return strings(id).DESCRIPTION;
    }

    public void upgradeDescription() {
        rawDescription = strings(cardID).UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void upgradeTimesUpgraded() {
        ++timesUpgraded;
        upgraded = true;
        name = getName(cardID) + "+" + timesUpgraded;
        initializeTitle();
    }

    public void changeCardImage(String img) {
        textureImg = ModHelper.cards(type, img);
        loadCardImage(textureImg);
    }
}
