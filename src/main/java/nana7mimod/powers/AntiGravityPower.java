package nana7mimod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.FlightPower;
import nana7mimod.helpers.ModHelper;

public class AntiGravityPower extends FlightPower {
    public static final String POWER_ID = ModHelper.id(AntiGravityPower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AntiGravityPower(AbstractCreature owner, int amount) {
        super(owner, amount);
        this.name = powerStrings.NAME;
    }

    @Override
    public void onRemove() {}

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
