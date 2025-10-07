package nana7mimod.helpers;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import com.badlogic.gdx.Gdx;
import com.google.gson.reflect.TypeToken;
import basemod.BaseMod;

public class GameStrings {
    public String NAME;
    public Boolean PLAYED;

    public GameStrings() {}

    public static Map<String, GameStrings> load() {
        String json = Gdx.files.internal(ModHelper.L10N("games.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        Type gameMap = new TypeToken<Map<String, GameStrings>>() {}.getType();
        return BaseMod.gson.fromJson(json, gameMap);
    }
}
