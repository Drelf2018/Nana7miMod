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

// 图片路径（大小128x128，可参考同目录的图片）
// private static final String IMG_PATH = ModHelper.relics(ID);
// 遗物未解锁时的轮廓。可以不使用。如果要使用，取消注释
// private static final String OUTLINE_PATH =
// "ExampleModResources/img/relics/MyRelic_Outline.png";
// ImageMaster.loadImage(IMG_PATH)
// 如果你需要轮廓图，取消注释下面一行并注释上面一行，不需要就删除
// super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER,
// LANDING_SOUND);

public class ATField extends CustomRelic {
    public static final String ID = ModHelper.id(ATField.class);

    private boolean isFirstTimePlayGame = true;

    public ATField() {
        super(ID, "bank.png", RelicTier.STARTER, LandingSound.MAGICAL);
    }

    // 初始化遗物描述
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractPlayer owner = AbstractDungeon.player;
        addToBot(new RelicAboveCreatureAction(owner, this));
        addToBot(new ApplyPowerAction(owner, owner, new ATFieldPower(owner, 1)));
        addToBot(new ApplyPowerAction(owner, owner, new InjuredPower(owner, 10 + AbstractDungeon.ascensionLevel)));
    }

    // 宽恕
    @Override
    public int onPlayerGainedBlock(float blockAmount) {
        ATFieldPower.addAmount(AbstractDungeon.player, -1);
        return super.onPlayerGainedBlock(blockAmount);
    }

    public static boolean getFirstTimePlayGame() {
        AbstractRelic r = AbstractDungeon.player.getRelic(ID);
        if (r == null || !(r instanceof ATField)) {
            return false;
        }
        ATField a = (ATField) r;
        if (a.isFirstTimePlayGame) {
            a.isFirstTimePlayGame = false;
            return true;
        }
        return false;
    }
}
