package nana7mimod.helpers;

import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.Settings;

public class ModHelper {
    public static final String NAME = "Nana7miMod";
    public static final String PRIFIX = NAME + ":";
    public static final String Resources = NAME + "Resources";

    public static String id(Class<?> cardType) {
        return PRIFIX + cardType.getSimpleName();
    }

    public static String image(String path) {
        return Resources + "/image/" + path;
    }

    public static String characters(String character, String path) {
        return Resources + "/image/characters/" + character + "/" + path;
    }

    public static String L10N(String path) {
        String local = Resources + "/localization/" + Settings.language.name();
        if (Gdx.files.internal(local).exists()) {
            return local + "/" + path;
        }
        return Resources + "/localization/ZHS/" + path;
    }

    public static String cards(CardType type, String id) {
        String name = type.name().toLowerCase();
        String path = ModHelper.image("cards/" + name + "/");
        String card = id.substring(PRIFIX.length()) + ".png";
        if (Gdx.files.internal(path + card).exists()) {
            return path + card;
        }
        return path + name.substring(0, 1).toUpperCase() + name.substring(1) + ".png";
    }

    public static String relics(String id) {
        return ModHelper.image("relics/" + id.substring(PRIFIX.length()) + ".png");
    }

    public static void loadStrings(Class<?> stringType) {
        BaseMod.loadCustomStringsFile(stringType, ModHelper.L10N(stringType.getSimpleName().toLowerCase().split("strings")[0] + "s.json"));
    }
}
