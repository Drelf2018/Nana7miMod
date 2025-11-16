package nana7mimod.relics;

import basemod.abstracts.CustomRelic;
import java.io.IOException;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.ui.FtueTip;
import com.megacrit.cardcrawl.ui.FtueTip.TipType;
import nana7mimod.cards.Base;
import nana7mimod.helpers.ModHelper;
import nana7mimod.modcore.Nana7miMod;
import nana7mimod.powers.ATFieldPower;
import nana7mimod.powers.InjuredPower;

public class ATField extends CustomRelic {
    public static final String ID = ModHelper.id(ATField.class);

    public ATField() {
        super(ID, ImageMaster.loadImage(ModHelper.relics(ID)), RelicTier.STARTER, LandingSound.MAGICAL);
        this.counter = 1;
    }

    private String setDescription(AbstractPlayer.PlayerClass c) {
        return DESCRIPTIONS[0] + (counter <= 0 ? 1 : counter) + DESCRIPTIONS[1];
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
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

    @Override
    public void atBattleStart() {
        if (!Nana7miMod.TutorialClosed) {
            AbstractDungeon.ftue = new FtueTip(name, Base.DIALOGUES.get(ID), 360.0F * Settings.scale, 760.0F * Settings.scale, TipType.RELIC);
            Nana7miMod.TutorialClosed = true;
            try {
                SpireConfig config = new SpireConfig("Nana7miMod", "Common");
                config.setBool("tutorialClosed", true);
                config.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        addToTop(new ApplyPowerAction(p, p, new InjuredPower(p, 10)));
        addToTop(new ApplyPowerAction(p, p, new ATFieldPower(p, counter)));
        addToTop(new RelicAboveCreatureAction(p, this));
    }
}
