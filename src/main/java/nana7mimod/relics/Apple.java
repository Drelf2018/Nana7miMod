package nana7mimod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import nana7mimod.helpers.ModHelper;

public class Apple extends CustomRelic {
    public static final String ID = ModHelper.id(Apple.class);

    public Apple() {
        super(ID, ImageMaster.loadImage(ModHelper.relics(ID)), RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    // 初始化遗物描述
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.increaseMaxHp(7, true);
    }

    @Override
    public void atBattleStart() {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new MakeTempCardInHandAction(new Miracle(), 1, false));
        addToBot(new MakeTempCardInDrawPileAction(new VoidCard(), 1, true, true));
    }
}
