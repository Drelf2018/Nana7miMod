package nana7mimod.characters;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import java.util.ArrayList;
import nana7mimod.actions.ClothingAction;
import nana7mimod.cards.Accept;
import nana7mimod.cards.Defend;
import nana7mimod.cards.Strike;
import nana7mimod.helpers.CharacterHelper;
import nana7mimod.patches.OnEnterRoomPatch.ClothingHandler;
import nana7mimod.powers.FirmPower;
import nana7mimod.cards.NightStrike;
import nana7mimod.relics.ATField;
import nana7mimod.stances.KyojinStance;
import nana7mimod.stances.KyojinStance.KyojinHandler;

public class Nana7mi extends Base implements ClothingHandler, KyojinHandler {
    public Nana7mi(String name) {
        super(name, Nana7mi.PlayerColorEnum.NANA7MI);
        // 人物对话气泡的大小，如果游戏中尺寸不对在这里修改（libgdx的坐标轴左下为原点）
        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 325.0F * Settings.scale);
    }

    // 初始卡组的 ID
    public ArrayList<String> getStartingDeck() {
        return list(Strike.ID, Strike.ID, Strike.ID, Strike.ID, NightStrike.ID, Defend.ID, Defend.ID, Defend.ID, Defend.ID, Accept.ID);
    }

    // 初始遗物的 ID
    public ArrayList<String> getStartingRelics() {
        return list(ATField.ID);
    }

    public CharInfo getCharInfo() {
        return new CharInfo(73, 138);
    }

    public String[] getOrbTextures() {
        return new String[] {"orb/layer.png"};
    }

    public void PutOnClothes() {
        for (int i = 0; i < 60; i++)
            AbstractDungeon.actionManager.addToBottom(new ClothingAction(image("images/" + i + ".png")));
        corpseImg = ImageMaster.loadImage(image("corpse2.png"));
    }

    public void TakeOffClothes() {
        img = ImageMaster.loadImage(image(getCharacterImage()));
        corpseImg = ImageMaster.loadImage(image("corpse.png"));
    }

    public class WaitAction extends AbstractGameAction {
        public WaitAction(float setDur) {
            this.duration = setDur;
            this.actionType = ActionType.WAIT;
        }

        public void update() {
            tickDuration();
        }
    }

    public void Kyojinka() {
        Texture clothes = KyojinStance.scale((FileTextureData) img.getTextureData(), 1.5F);
        AbstractDungeon.actionManager.addToTop(new ClothingAction(clothes));
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.3F));
    }

    public void Kaijo() {
        if (getPower(FirmPower.POWER_ID) instanceof FirmPower)
            img = ImageMaster.loadImage(image("images/59.png"));
        else
            img = ImageMaster.loadImage(image("images/0.png"));
    }

    @Override
    public String getCharacterImage() {
        return "images/0.png";
    }

    @Override
    public float[] getHitbox() {
        return new float[] {10.0F, 0.0F, 220.0F, 350.0F};
    }

    // 翻牌事件出现的你的职业牌（一般设为打击）
    @Override
    public AbstractCard getStartCardForEvent() {
        return new Strike();
    }

    // 卡牌颜色
    @Override
    public AbstractCard.CardColor getCardColor() {
        return Nana7mi.PlayerColorEnum.NANA7MI_BLUE;
    }

    // 卡牌轨迹颜色
    @Override
    public Color getCardTrailColor() {
        return CharacterHelper.NANA7MI_BLUE;
    }

    // 卡牌选择界面选择该牌的颜色
    @Override
    public Color getCardRenderColor() {
        return CharacterHelper.NANA7MI_BLUE;
    }

    // 打心脏的颜色
    @Override
    public Color getSlashAttackColor() {
        return CharacterHelper.NANA7MI_BLUE;
    }

    // 碎心图片边框
    @Override
    public Texture getCutsceneBg() {
        return ImageMaster.loadImage("images/scenes/blueBg.jpg");
    }

    // 碎心图片
    @Override
    public ArrayList<CutscenePanel> getCutscenePanels() {
        // 第二个参数表示出现图片时播放的音效
        ArrayList<CutscenePanel> panels = new ArrayList<>();
        panels.add(new CutscenePanel(image("victory1.png"), "EVENT_VAMP_BITE"));
        panels.add(new CutscenePanel(image("victory2.png")));
        panels.add(new CutscenePanel(image("victory3.png")));
        return panels;
    }

    // 创建人物实例
    @Override
    public AbstractPlayer newInstance() {
        return new Nana7mi(name);
    }

    // 注意此处必须是在类内部的静态嵌套类中定义的新枚举值
    public static class PlayerColorEnum {
        // 修改为你的人物名称，确保不会与其他mod冲突
        @SpireEnum
        public static PlayerClass NANA7MI;

        // 将 CardColor 和 LibraryType 的变量名改为你的人物的颜色名称，确保不会与其他mod冲突！
        @SpireEnum
        public static AbstractCard.CardColor NANA7MI_BLUE;
    }

    public static class PlayerLibraryEnum {
        // 这个变量未被使用（呈现灰色）是正常的，并且需要与上面的颜色名称保持一致！
        @SpireEnum
        public static CardLibrary.LibraryType NANA7MI_BLUE;
    }
}
