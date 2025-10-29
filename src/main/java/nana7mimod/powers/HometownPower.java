package nana7mimod.powers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import nana7mimod.helpers.ModHelper;
import nana7mimod.stances.KyojinStance;

public class HometownPower extends Base {
    public static final String POWER_ID = ModHelper.id(HometownPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HometownPower(AbstractCreature owner, int amount) {
        super(POWER_ID, powerStrings.NAME, owner, amount, PowerType.BUFF);
    }

    @Override
    public void onVictory() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.stance instanceof KyojinStance) {
            ((KyojinStance) p.stance).heal(amount);
        } else if (p.currentHealth > 0) {
            p.heal(amount);
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
