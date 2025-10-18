package nana7mimod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import nana7mimod.helpers.ModHelper;

public class PityingPower extends Base {
    public static final String POWER_ID = ModHelper.id(PityingPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PityingPower(AbstractCreature owner) {
        super(POWER_ID, powerStrings.NAME, owner, -1, PowerType.BUFF);
    }

    // 怜悯
    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (info.type == DamageType.NORMAL && info.owner != owner) {
            flashWithoutSound();
            addToBot(new ApplyPowerAction(info.owner, owner, new GuiltyPower(info.owner, owner, damageAmount)));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
