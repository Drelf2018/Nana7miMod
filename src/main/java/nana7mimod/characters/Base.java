package nana7mimod.characters;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import nana7mimod.helpers.ModHelper;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Base extends CustomPlayer {
    // 人物的本地化文本
    private CharacterStrings charStrings;
    // 人物类名小写
    private String charName;

    public Base(String name, PlayerClass player, EnergyOrbInterface energyOrbInterface, String model, String animation) {
        super(name, player, energyOrbInterface, model, animation);
        // 设置能量图标
        if (energyOrbInterface == null) {
            String[] orbTextures = Arrays.stream(this.getOrbTextures()).map(s -> image(s)).toArray(String[]::new);
            this.energyOrb = new CustomEnergyOrb(orbTextures, image(this.getOrbVfx()), this.getLayerSpeed());
        }
        // 具体初始化人物
        String stand = this.getStandImage();
        float[] hitbox = this.getHitbox();
        this.initializeClass(//
                stand == "" ? stand : image(stand), // 人物立绘
                image("shoulder2.png"), // 火堆后图像
                image("shoulder1.png"), // 火堆前图像
                image("corpse.png"), // 人物死亡图像
                this.getLoadout(), // 基础信息
                hitbox[0], hitbox[1], hitbox[2], hitbox[3], // 人物碰撞箱大小，越大的人物模型这个越大
                new EnergyManager(this.getCharInfo().energy) // 初始每回合的能量
        );
    }

    public Base(String name, PlayerClass player, String model, String animation) {
        this(name, player, (EnergyOrbInterface) null, model, animation);
    }

    public Base(String name, PlayerClass player) {
        this(name, player, null, null);
    }

    public CharacterStrings strings() {
        if (this.charStrings == null) {
            this.charStrings = CardCrawlGame.languagePack.getCharacterString(ModHelper.id(this.getClass()));
        }
        return this.charStrings;
    }

    public String image(String path) {
        if (this.charName == null) {
            this.charName = this.getClass().getSimpleName().toLowerCase();
        }
        return ModHelper.characters(this.charName, path);
    }

    public static ArrayList<String> list(String... items) {
        if (items == null) {
            return new ArrayList<>(); // 返回空列表
        }
        return new ArrayList<>(Arrays.asList(items));
    }

    // 初始卡组的ID，可直接写或引用变量
    public abstract ArrayList<String> getStartingDeck();

    // 初始遗物的ID，可以先写个原版遗物凑数
    public abstract ArrayList<String> getStartingRelics();

    public class CharInfo {
        public int gold;
        public int maxHp;
        public int energy = 3;
        public int maxOrbs = 0;
        public int cardDraw = 5;
        public int currentHp = 50;

        CharInfo(int maxHP, int gold) {
            this.currentHp = this.maxHp = maxHP;
            this.gold = gold;
        }

        CharInfo(int maxHP, int gold, int energy) {
            this(maxHP, gold);
            this.energy = energy;
        }
    }

    // 初始信息
    public abstract CharInfo getCharInfo();

    @Override
    public CharSelectInfo getLoadout() {
        CharInfo info = this.getCharInfo();
        return new CharSelectInfo(//
                strings().NAMES[0], // 人物名称
                strings().TEXT[0], // 人物介绍
                info.currentHp, // 当前生命
                info.maxHp, // 最大生命
                info.maxOrbs, // 最大充能球栏数
                info.gold, // 初始金币
                info.cardDraw, // 每回合抽牌数
                this, // 人物自身
                this.getStartingRelics(), // 初始遗物
                this.getStartingDeck(), // 初始牌组
                false//
        );
    }

    // 战斗界面左下角能量图标的每个图层
    public abstract String[] getOrbTextures();

    // 能量特效图层
    public String getOrbVfx() {
        return "orb/vfx.png";
    }

    // 每个图层的旋转速度
    public float[] getLayerSpeed() {
        return null;
    }

    // 战斗界面静态立绘
    public String getStandImage() {
        return "stand.png";
    }

    // 获取碰撞箱
    public float[] getHitbox() {
        return new float[] {0.0F, -5.0F, 240.0F, 270.0F};
    }

    // 高进阶带来的生命值损失
    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    // 卡牌的能量字体
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    // 人物选择界面点击你的人物按钮时触发的方法，这里为屏幕轻微震动
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    // 自定义模式选择你的人物时播放的音效
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    // 吸血鬼事件文本，主要是他（索引为0）和她（索引为1）的区别（机器人另外）
    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }

    // 游戏中左上角人物名称
    @Override
    public String getTitle(PlayerClass playerClass) {
        return strings().NAMES[0];
    }

    // 选择角色界面人物名字
    @Override
    public String getLocalizedCharacterName() {
        return strings().NAMES[0];
    }

    // 第三章面对心脏说的话（例如战士是“你握紧了你的长刀……”）
    @Override
    public String getSpireHeartText() {
        return strings().TEXT[1];
    }

    // 第三章面对心脏造成伤害时的特效
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[] {AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
                AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }

    // 碎心图片
    @Override
    public ArrayList<CutscenePanel> getCutscenePanels() {
        // 第二个参数表示出现图片时播放的音效
        ArrayList<CutscenePanel> panels = new ArrayList<>();
        panels.add(new CutscenePanel(image("victory1.png"), "ATTACK_MAGIC_FAST_1"));
        panels.add(new CutscenePanel(image("victory2.png")));
        panels.add(new CutscenePanel(image("victory3.png")));
        return panels;
    }
}
