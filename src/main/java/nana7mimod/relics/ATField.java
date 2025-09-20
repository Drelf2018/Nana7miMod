package nana7mimod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
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

    public ATField() {
        super(ID, "bank.png", RelicTier.STARTER, LandingSound.MAGICAL);
        this.counter = 1;
    }

    private String setDescription(AbstractPlayer.PlayerClass c) {
        return DESCRIPTIONS[0] + (counter == 0 ? 1 : counter) + DESCRIPTIONS[1];
    }

    // 初始化遗物描述
    @Override
    public String getUpdatedDescription() {
        return setDescription(null);
    }

    // 更新遗物描述
    @Override
    public void updateDescription(AbstractPlayer.PlayerClass c) {
        description = setDescription(c);
        tips.clear();
        tips.add(new PowerTip(this.name, this.description));
        initializeTips();
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new RelicAboveCreatureAction(p, this));
        addToBot(new ApplyPowerAction(p, p, new ATFieldPower(p, counter)));
        addToBot(new ApplyPowerAction(p, p, new InjuredPower(p, 10 + AbstractDungeon.ascensionLevel)));
    }
}
