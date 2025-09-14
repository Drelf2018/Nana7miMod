package nana7mimod.cards;

import java.util.ArrayList;
import java.util.Collections;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.QuestionCard;
import basemod.abstracts.CustomSavable;
import nana7mimod.helpers.ModHelper;

public class Ask extends Base implements CustomSavable<Boolean> {
    public static final String ID = ModHelper.id(Ask.class);

    public static Boolean isFirstTimePlayAsk = true;

    public Ask() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardsToPreview = new Game();
    }

    // 保存
    @Override
    public Boolean onSave() {
        return isFirstTimePlayAsk;
    }

    // 读取
    @Override
    public void onLoad(Boolean save) {
        if (save != null)
            isFirstTimePlayAsk = save;
    }

    private ArrayList<AbstractCard> generateCardChoices(int total, int incorrect) {
        ArrayList<AbstractCard> derp = new ArrayList<AbstractCard>();
        if (AbstractDungeon.player.hasRelic(QuestionCard.ID))
            ++total;
        for (int i = 0; i < incorrect; ++i)
            derp.add(Game.Incorrect(AbstractDungeon.cardRandomRng.random(Game.INCORRECT - 1)));
        if (isFirstTimePlayAsk != null && isFirstTimePlayAsk) {
            derp.add(Game.Correct(65));
            isFirstTimePlayAsk = false;
        }
        while (derp.size() < total)
            derp.add(Game.Correct(AbstractDungeon.cardRandomRng.random(Game.CORRECT - 1)));
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
        if (upgraded)
            addToBot(new ChooseOneAction(generateCardChoices(3, 2)));
    }
}
