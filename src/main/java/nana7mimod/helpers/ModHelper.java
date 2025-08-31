package nana7mimod.helpers;

import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.Settings;

public class ModHelper {
    public static final String NAME = "Nana7miMod";
    public static final String PRIFIX = NAME + ":";
    public static final String RESOURCES = NAME + "Resources";

    public static String id(Class<?> cardType) {
        return PRIFIX + cardType.getSimpleName();
    }

    public static String unwrap(String id) {
        if (id.startsWith(PRIFIX))
            return id.substring(PRIFIX.length());
        return id;
    }

    public static String characters(String character, String path) {
        return RESOURCES + "/image/characters/" + character + "/" + path;
    }

    public static String L10N(String path) {
        String local = RESOURCES + "/localization/" + Settings.language.name();
        if (Gdx.files.internal(local).exists())
            return local + "/" + path;
        return RESOURCES + "/localization/ZHS/" + path;
    }

    public static String cards(CardType type, String id) {
        String path = RESOURCES + "/image/cards/" + type.name().toLowerCase();
        String card = path + "/" + ModHelper.unwrap(id) + ".png";
        if (Gdx.files.internal(card).exists())
            return card;
        return path + ".png";
    }

    public static String relics(String id) {
        return RESOURCES + "/image/relics/" + ModHelper.unwrap(id) + ".png";
    }

    public static void loadStrings(Class<?> stringType) {
        String local = ModHelper.L10N(stringType.getSimpleName().toLowerCase().replace("strings", "s.json"));
        BaseMod.loadCustomStringsFile(stringType, local);
    }

    public static int countGames(boolean correct) {
        String path = RESOURCES + "/image/cards/status/game/" + (correct ? "correct" : "incorrect");
        int left = 0;
        int right = 1;

        while (Gdx.files.internal(path + "/" + right + ".png").exists()) {
            left = right;
            right *= 2;
        }

        while (left < right) {
            int mid = (left + right) / 2;
            if (Gdx.files.internal(path + "/" + mid + ".png").exists())
                left = mid + 1;
            else
                right = mid;
        }

        return left;
    }
}
