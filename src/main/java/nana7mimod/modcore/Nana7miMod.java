package nana7mimod.modcore;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.AddAudioSubscriber;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import java.io.IOException;
import java.util.Properties;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import nana7mimod.cards.Base;
import nana7mimod.helpers.CharacterHelper;
import nana7mimod.helpers.ModHelper;
import nana7mimod.relics.ATField;
import nana7mimod.relics.Apple;
import nana7mimod.relics.BlackATField;
import nana7mimod.characters.Nana7mi;

@SpireInitializer
public class Nana7miMod implements PostInitializeSubscriber, AddAudioSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber,
        EditRelicsSubscriber, EditKeywordsSubscriber {

    public static boolean TutorialClosed;

    public static boolean AskPlayed;

    public static boolean StoryMode;

    // 订阅事件
    // 向 basemod 注册颜色
    public Nana7miMod() {
        BaseMod.subscribe(this);
        CharacterHelper.addColor(Nana7mi.class, Nana7mi.PlayerColorEnum.NANA7MI_BLUE, CharacterHelper.NANA7MI_BLUE);
    }

    public static void initialize() {
        new Nana7miMod();
    }

    public void receivePostInitialize() {
        try {
            Properties defaults = new Properties();
            defaults.setProperty("tutorialClosed", "false");
            defaults.setProperty("askPlayed", "false");
            defaults.setProperty("storyMode", "false");
            SpireConfig config = new SpireConfig("Nana7miMod", "Common", defaults);
            TutorialClosed = config.getBool("tutorialClosed");
            AskPlayed = config.getBool("askPlayed");
            StoryMode = config.getBool("storyMode");
        } catch (IOException e) {
            e.printStackTrace();
        }
        UIStrings UIStrings = CardCrawlGame.languagePack.getUIString(ModHelper.id("ConfigMenu"));
        ModPanel settingsPanel = new ModPanel();
        ModLabeledToggleButton storyModeBtn = new ModLabeledToggleButton(UIStrings.TEXT[0], UIStrings.TEXT[1], 350.0F, 700.0F, Settings.CREAM_COLOR,
                FontHelper.charDescFont, StoryMode, settingsPanel, l -> {
                }, button -> {
                    try {
                        StoryMode = button.enabled;
                        SpireConfig config = new SpireConfig("Nana7miMod", "Common");
                        config.setBool("storyMode", button.enabled);
                        config.save();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        settingsPanel.addUIElement(storyModeBtn);
        BaseMod.registerModBadge(ImageMaster.loadImage(ModHelper.RESOURCES + "/image/nana7mi.png"), ModHelper.NAME, "脆鲨12138", "DESC", settingsPanel);
    }

    // 向 basemod 注册音频
    @Override
    public void receiveAddAudio() {
        for (String id : Base.DIALOGUES.keySet())
            BaseMod.addAudio(id, ModHelper.audio(id));
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
        BaseMod.addRelic(new Apple(), RelicType.SHARED);
        BaseMod.addRelic(new ATField(), RelicType.SHARED);
        BaseMod.addRelic(new BlackATField(), RelicType.SHARED);
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
        BaseMod.loadCustomStringsFile(UIStrings.class, ModHelper.strings(UIStrings.class));// UI
        BaseMod.loadCustomStringsFile(CardStrings.class, ModHelper.strings(CardStrings.class));// 卡牌
        BaseMod.loadCustomStringsFile(RelicStrings.class, ModHelper.strings(RelicStrings.class)); // 遗物
        BaseMod.loadCustomStringsFile(PowerStrings.class, ModHelper.strings(PowerStrings.class)); // 能力
        BaseMod.loadCustomStringsFile(StanceStrings.class, ModHelper.strings(StanceStrings.class)); // 姿态
        BaseMod.loadCustomStringsFile(CharacterStrings.class, ModHelper.strings(CharacterStrings.class));// 人物
    }
}
