package nana7mimod.helpers;

import com.badlogic.gdx.graphics.Color;
import basemod.BaseMod;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class CharacterHelper {
    public static final Color NANA7MI_BLUE = new Color(65.0F / 255.0F, 139.0F / 255.0F, 222.0F / 255.0F, 1.0F);

    public static void addColor(Class<?> charType, CardColor cardColor, Color color) {
        String charName = charType.getSimpleName().toLowerCase();
        BaseMod.addColor(cardColor, color, color, color, color, color, color, color, //
                ModHelper.characters(charName, "card/attack_512.png"), //
                ModHelper.characters(charName, "card/skill_512.png"), //
                ModHelper.characters(charName, "card/power_512.png"), //
                ModHelper.characters(charName, "card/energy.png"), //
                ModHelper.characters(charName, "card/attack_1024.png"), //
                ModHelper.characters(charName, "card/skill_1024.png"), //
                ModHelper.characters(charName, "card/power_1024.png"), //
                ModHelper.characters(charName, "card/energy_p.png"), //
                ModHelper.characters(charName, "card/card_energy.png"));
    }

    public static void addCharacter(AbstractPlayer player, String button, String portrait) {
        String charName = player.getClass().getSimpleName().toLowerCase();
        BaseMod.addCharacter(player, ModHelper.characters(charName, button), ModHelper.characters(charName, portrait));
    }

    public static void addCharacter(AbstractPlayer player) {
        CharacterHelper.addCharacter(player, "button.png", "portrait.png");
    }
}
