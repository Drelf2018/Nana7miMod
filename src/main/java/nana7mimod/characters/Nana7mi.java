package nana7mimod.characters;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import java.util.ArrayList;
import nana7mimod.cards.Accept;
import nana7mimod.cards.Defend;
import nana7mimod.cards.Strike;
import nana7mimod.helpers.CharacterHelper;
import nana7mimod.cards.NightStrike;
import nana7mimod.relics.ATField;

public class Nana7mi extends Base {
    public Nana7mi(String name) {
        super(name, Nana7mi.PlayerColorEnum.NANA7MI);
    }

    // 人物对话气泡的大小，如果游戏中尺寸不对在这里修改（libgdx的坐标轴左下为原点）
    // this.dialogX = (this.drawX + 0.0F * Settings.scale);
    // this.dialogY = (this.drawY + 150.0F * Settings.scale);
    // 如果你的人物没有动画，那么这些不需要写
    // this.loadAnimation(ModHelper.image("characters/character.atlas",
    // ModHelper.image("characters/character.json", 1.8F);
    // AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
    // e.setTime(e.getEndTime() * MathUtils.random());
    // e.setTimeScale(1.2F);

    // 初始卡组的 ID
    public ArrayList<String> getStartingDeck() {
        return list(Strike.ID, Strike.ID, Strike.ID, Strike.ID, NightStrike.ID, //
                Defend.ID, Defend.ID, Defend.ID, Defend.ID, Accept.ID);
    }

    // 初始遗物的 ID
    public ArrayList<String> getStartingRelics() {
        return list(ATField.ID);
    }

    public CharInfo getCharInfo() {
        return new CharInfo(73, 138);
    }

    public String[] getOrbTextures() {
        return new String[] {//
                "orb/layer5.png", "orb/layer4.png", "orb/layer3.png", "orb/layer2.png", "orb/layer1.png", "orb/layer6.png", //
                "orb/layer5d.png", "orb/layer4d.png", "orb/layer3d.png", "orb/layer2d.png", "orb/layer1d.png"//
        };
    }

    @Override
    public float[] getLayerSpeed() {
        return new float[] {-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};
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
        return CharacterHelper.Nana7mi.COLOR;
    }

    // 卡牌选择界面选择该牌的颜色
    @Override
    public Color getCardRenderColor() {
        return CharacterHelper.Nana7mi.COLOR;
    }

    // 打心脏的颜色
    @Override
    public Color getSlashAttackColor() {
        return CharacterHelper.Nana7mi.COLOR;
    }

    // 创建人物实例
    @Override
    public AbstractPlayer newInstance() {
        return new Nana7mi(this.name);
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
