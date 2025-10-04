package nana7mimod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.InjuredPower;
import nana7mimod.powers.LostPower;
import nana7mimod.powers.MonitorPower;
import nana7mimod.powers.NightPower;
import nana7mimod.powers.PadoruPower;
import nana7mimod.powers.PityingPower;
import nana7mimod.powers.SilencePower;
import nana7mimod.powers.SulkingPower;
import nana7mimod.powers.UnnPower;
import nana7mimod.powers.ATFieldPower;
import nana7mimod.powers.BetrayPower;
import nana7mimod.powers.BouquetPower;
import nana7mimod.powers.CrackPower;
import nana7mimod.powers.CrazyPower;
import nana7mimod.powers.DormantPower;
import nana7mimod.powers.EscapePower;
import nana7mimod.powers.FirmPower;
import nana7mimod.powers.GetHeritagePower;
import nana7mimod.powers.GuiltyPower;
import nana7mimod.powers.HaterPower;
import nana7mimod.powers.HeritagePower;
import nana7mimod.powers.HometownPower;
import nana7mimod.powers.HotSpringPower;
import nana7mimod.powers.InfatuationPower;

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
        tips.add(new PowerTip(this.name, this.description));
        initializeTips();
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new RelicAboveCreatureAction(p, this));
        addToBot(new ApplyPowerAction(p, p, new ATFieldPower(p, counter)));
        addToBot(new ApplyPowerAction(p, p, new InjuredPower(p, 20 + AbstractDungeon.ascensionLevel)));
    }
}
