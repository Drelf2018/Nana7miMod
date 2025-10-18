package nana7mimod.modcore;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.StanceStrings;
import nana7mimod.cards.Base;
import nana7mimod.helpers.CharacterHelper;
import nana7mimod.helpers.ModHelper;
import nana7mimod.relics.ATField;
import nana7mimod.characters.Nana7mi;

@SpireInitializer
public class Nana7miMod
        implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber {

    // 订阅事件
    // 向 basemod 注册颜色
    public Nana7miMod() {
        BaseMod.subscribe(this);
        CharacterHelper.addColor(Nana7mi.class, Nana7mi.PlayerColorEnum.NANA7MI_BLUE, CharacterHelper.NANA7MI_BLUE);
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
        CharacterHelper.addCharacter(new Nana7mi(CardCrawlGame.playerName));
    }

    // 向 basemod 注册关键词
    @Override
    public void receiveEditKeywords() {
        Keyword[] keywords = BaseMod.gson.fromJson(ModHelper.keywords(), Keyword[].class);
        for (Keyword keyword : keywords)
            BaseMod.addKeyword(ModHelper.NAME.toLowerCase(), keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
    }

    // 向 basemod 加载相应语言的本地化内容
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, ModHelper.strings(CardStrings.class));// 卡牌
        BaseMod.loadCustomStringsFile(RelicStrings.class, ModHelper.strings(RelicStrings.class)); // 遗物
        BaseMod.loadCustomStringsFile(PowerStrings.class, ModHelper.strings(PowerStrings.class)); // 能力
        BaseMod.loadCustomStringsFile(StanceStrings.class, ModHelper.strings(StanceStrings.class)); // 姿态
        BaseMod.loadCustomStringsFile(CharacterStrings.class, ModHelper.strings(CharacterStrings.class));// 人物
    }
}
