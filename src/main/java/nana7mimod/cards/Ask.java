package nana7mimod.cards;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.QuestionCard;
import nana7mimod.helpers.ModHelper;
import nana7mimod.modcore.Nana7miMod;

public class Ask extends Base {
    public static final String ID = ModHelper.id(Ask.class);

    public Ask() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = 1;
        this.cardsToPreview = new Game();
    }

    private ArrayList<AbstractCard> generateCardChoices(int total, int incorrect) {
        ArrayList<AbstractCard> derp = new ArrayList<AbstractCard>();
        if (AbstractDungeon.player.hasRelic(QuestionCard.ID))
            ++total;
        // 确定非重复错误卡号
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        while (indexes.size() < incorrect) {
            int index = AbstractDungeon.cardRandomRng.random(Game.UNPLAYED.length - 1);
            if (!indexes.contains(index)) {
                indexes.add(index);
                derp.add(Game.Unplayed(index));
            }
        }
        // 添加彩蛋卡
        if (!Nana7miMod.AskPlayed) {
            Nana7miMod.AskPlayed = true;
            derp.add(Game.SlayTheSpire());
            try {
                SpireConfig config = new SpireConfig("Nana7miMod", "Common");
                config.setBool("askPlayed", true);
                config.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 确定非重复正确卡号
        indexes.clear();
        while (derp.size() < total) {
            int index = AbstractDungeon.cardRandomRng.random(Game.PLAYED.length - 1);
            if (!indexes.contains(index)) {
                indexes.add(index);
                derp.add(Game.Played(index));
            }
        }
        // 洗牌
        Collections.shuffle(derp);
        return derp;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playSound(ID);
        addToBot(new ChooseOneAction(generateCardChoices(3, 1)));
        if (upgraded)
            addToBot(new ChooseOneAction(generateCardChoices(3, 2)));
    }
}
