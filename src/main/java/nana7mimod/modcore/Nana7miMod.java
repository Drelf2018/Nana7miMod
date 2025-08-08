package nana7mimod.modcore;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import java.nio.charset.StandardCharsets;
import nana7mimod.cards.Base;
import nana7mimod.helpers.CharacterHelper;
import nana7mimod.helpers.ModHelper;
import nana7mimod.relics.ATField;
import nana7mimod.characters.Nana7mi;

@SpireInitializer
public class Nana7miMod implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber {

    // 订阅事件
    // 向 basemod 注册颜色
    public Nana7miMod() {
        BaseMod.subscribe(this);
        new CharacterHelper.Nana7mi(Nana7mi.PlayerColorEnum.NANA7MI_BLUE);
    }

    public static void initialize() {
        new Nana7miMod();
    }

    // 向 basemod 注册卡牌
    @Override
    public void receiveEditCards() {
        new AutoAdd(ModHelper.NAME).packageFilter(Base.class).setDefaultSeen(true).cards();
    }

    // 向 basemod 注册遗物
    @Override
    public void receiveEditRelics() {
        // RelicType 表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
        BaseMod.addRelic(new ATField(), RelicType.SHARED);
    }

    // 向 basemod 注册人物
    @Override
    public void receiveEditCharacters() {
        new CharacterHelper.Nana7mi(new Nana7mi(CardCrawlGame.playerName));
    }

    // 向 basemod 注册关键词
    @Override
    public void receiveEditKeywords() {
        FileHandle file = Gdx.files.internal(ModHelper.L10N("keywords.json"));
        String json = file.readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = new Gson().fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(ModHelper.NAME.toLowerCase(), keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    // 加载相应语言的本地化内容
    public void receiveEditStrings() {
        ModHelper.loadStrings(CardStrings.class); // 卡牌
        ModHelper.loadStrings(RelicStrings.class); // 遗物
        ModHelper.loadStrings(PowerStrings.class); // 能力
        ModHelper.loadStrings(CharacterStrings.class); // 人物
    }
}
