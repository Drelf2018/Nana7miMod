package nana7mimod.helpers;

import com.badlogic.gdx.graphics.Color;
import basemod.BaseMod;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class CharacterHelper {

    public static class Nana7mi extends CharacterHelper {
        public static final Color COLOR = new Color(65.0F / 255.0F, 139.0F / 255.0F, 222.0F / 255.0F, 1.0F);

        public Nana7mi(CardColor cardColor) {
            addColor(cardColor, COLOR);
        }

        public Nana7mi(AbstractPlayer player) {
            addCharacter(player);
        }
    }

    public void addColor(CardColor cardColor, Color color) {
        String charName = getClass().getSimpleName().toLowerCase();
        BaseMod.addColor(cardColor, color, color, color, color, color, color, color, //
                ModHelper.characters(charName, "card/attack_512.png"), //
                ModHelper.characters(charName, "card/skill_512.png"), //
                ModHelper.characters(charName, "card/power_512.png"), //
                ModHelper.characters(charName, "card/cost_orb.png"), //
                ModHelper.characters(charName, "card/attack_1024.png"), //
                ModHelper.characters(charName, "card/skill_1024.png"), //
                ModHelper.characters(charName, "card/power_1024.png"), //
                ModHelper.characters(charName, "card/cost.png"), //
                ModHelper.characters(charName, "card/cost_small.png"));
    }

    public void addCharacter(AbstractPlayer player) {
        String charName = getClass().getSimpleName().toLowerCase();
        BaseMod.addCharacter(player, //
                ModHelper.characters(charName, "button.png"), //
                ModHelper.characters(charName, "portrait.png"));
    }
}
