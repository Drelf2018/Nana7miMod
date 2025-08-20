package nana7mimod.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.QuestionCard;
import nana7mimod.helpers.ModHelper;
import nana7mimod.relics.ATField;

public class Ask extends Base {
    public static final String ID = ModHelper.id(Ask.class);

    public Ask() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardsToPreview = new Game();
    }

    private ArrayList<AbstractCard> generateCardChoices(int total, int incorrect) {
        ArrayList<AbstractCard> derp = new ArrayList<AbstractCard>();
        if (AbstractDungeon.player.hasRelic(QuestionCard.ID)) {
            total++;
        }
        Random random = new Random();
        for (int i = 0; i < incorrect; i++) {
            derp.add(Game.Incorrect(random.nextInt(Game.INCORRECT)));
        }
        if (ATField.getFirstTimePlayGame()) {
            derp.add(Game.Correct(65));
        }
        while (derp.size() < total) {
            derp.add(Game.Correct(random.nextInt(Game.CORRECT)));
        }
        Collections.shuffle(derp);
        return derp;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChooseOneAction(generateCardChoices(3, 1)));
        if (upgraded) {
            addToBot(new ChooseOneAction(generateCardChoices(3, 2)));
        }
    }
}
