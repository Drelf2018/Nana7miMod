package nana7mimod.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;
import nana7mimod.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class PityingPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.id(PityingPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PityingPower(AbstractCreature owner) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        this.description = DESCRIPTIONS[0];

        loadRegion("hello");
    }

    // 怜悯
    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (info.type == DamageType.NORMAL) {
            flashWithoutSound();
            addToBot(new ApplyPowerAction(info.owner, owner, new GuiltyPower(info.owner, owner, damageAmount)));
        }
    }
}
