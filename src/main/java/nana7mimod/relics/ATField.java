package nana7mimod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.InjuredPower;
import nana7mimod.powers.ATFieldPower;

public class ATField extends CustomRelic {
    public static final String ID = ModHelper.id(ATField.class);
    // 图片路径（大小128x128，可参考同目录的图片）
    // private static final String IMG_PATH = ModHelper.relics(ID);
    // 遗物未解锁时的轮廓。可以不使用。如果要使用，取消注释
    // private static final String OUTLINE_PATH =
    // "ExampleModResources/img/relics/MyRelic_Outline.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public ATField() {
        super(ID, "bank.png", RELIC_TIER, LANDING_SOUND);
        // ImageMaster.loadImage(IMG_PATH)
        // 如果你需要轮廓图，取消注释下面一行并注释上面一行，不需要就删除
        // super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER,
        // LANDING_SOUND);
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new ATField();
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractPlayer owner = AbstractDungeon.player;
        addToTop(new ApplyPowerAction(owner, owner, new InjuredPower(owner, 20 + AbstractDungeon.ascensionLevel)));
        addToTop(new ApplyPowerAction(owner, owner, new ATFieldPower(owner, 1)));
        addToTop(new RelicAboveCreatureAction(owner, this));
    }

    // 宽恕
    @Override
    public int onPlayerGainedBlock(float blockAmount) {
        ATFieldPower.addAmount(AbstractDungeon.player, -1);
        return super.onPlayerGainedBlock(blockAmount);
    }
}
