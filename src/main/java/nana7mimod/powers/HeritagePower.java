package nana7mimod.powers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import nana7mimod.helpers.ModHelper;

public class HeritagePower extends Base {
    public static final String POWER_ID = ModHelper.id(HeritagePower.class);

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HeritagePower(AbstractCreature owner, int amount) {
        super(POWER_ID, powerStrings.NAME, owner, amount, PowerType.DEBUFF);
    }

    @Override
    public void onDeath() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.getPower(GetHeritagePower.POWER_ID) instanceof GetHeritagePower) {
            p.gainGold(amount);
            for (int i = 0; i < amount; ++i)
                AbstractDungeon.effectList.add(new GainPennyEffect(p, owner.hb.cX, owner.hb.cY, p.hb.cX, p.hb.cY, true));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
