package nana7mimod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.ATFieldPower;
import nana7mimod.powers.InjuredPower;

public class BlackATField extends CustomRelic {
    public static final String ID = ModHelper.id(BlackATField.class);

    public BlackATField() {
        super(ID, ImageMaster.loadImage(ModHelper.relics(ID)), RelicTier.BOSS, LandingSound.MAGICAL);
        this.counter = 1;
    }

    private String setDescription(AbstractPlayer.PlayerClass c) {
        return DESCRIPTIONS[0] + (counter <= 0 ? 1 : counter) + DESCRIPTIONS[1];
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(ATField.ID);
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
        addToTop(new ApplyPowerAction(p, p, new InjuredPower(p, 5)));
        addToTop(new ApplyPowerAction(p, p, new ATFieldPower(p, counter)));
        addToTop(new RelicAboveCreatureAction(p, this));
    }
}
