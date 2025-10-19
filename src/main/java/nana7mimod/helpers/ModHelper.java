package nana7mimod.helpers;

import java.nio.charset.StandardCharsets;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.Settings;

public class ModHelper {
    public static final String NAME = "Nana7miMod";
    public static final String PRIFIX = NAME + ":";
    public static final String RESOURCES = NAME + "Resources";

    public static String id(String s) {
        return PRIFIX + s;
    }

    public static String id(Class<?> cardType) {
        return id(cardType.getSimpleName());
    }

    public static String unwrap(String id) {
        if (id.startsWith(PRIFIX))
            return id.substring(PRIFIX.length());
        return id;
    }

    public static String characters(String character, String path) {
        return RESOURCES + "/image/characters/" + character + "/" + path;
    }

    public static String music(String name) {
        return RESOURCES + "/music/" + name + ".mp3";
    }

    public static String audio(String id) {
        return RESOURCES + "/audio/" + unwrap(id) + ".mp3";
    }

    public static String cards(String type, String id) {
        return RESOURCES + "/image/cards/" + type + "/" + unwrap(id) + ".png";
    }

    public static String cards(CardType type, String id) {
        return cards(type.name().toLowerCase(), id);
    }

    public static String relics(String id) {
        return RESOURCES + "/image/relics/" + unwrap(id) + ".png";
    }

    public static String L10N(String path) {
        String local = RESOURCES + "/localization/" + Settings.language.name();
        if (Gdx.files.internal(local).exists())
            return local + "/" + path;
        return RESOURCES + "/localization/ZHS/" + path;
    }

    public static String strings(Class<?> stringType) {
        return L10N(stringType.getSimpleName().toLowerCase().replace("strings", "s.json"));
    }

    public static String file(String path) {
        return Gdx.files.internal(L10N(path)).readString(String.valueOf(StandardCharsets.UTF_8));
    }

    public static String dialogues() {
        return file("dialogues.json");
    }

    public static String games() {
        return file("games.json");
    }

    public static String keywords() {
        return file("keywords.json");
    }
}
