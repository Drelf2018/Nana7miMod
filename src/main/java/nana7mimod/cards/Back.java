package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.DormantPower;

public class Back extends Base {
    public static final String ID = ModHelper.id(Back.class);

    public Back() {
        super(ID, CardCost.CN, CardType.STATUS, CardTarget.NONE);
    }

    public void triggerWhenDrawn() {
        addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, DormantPower.POWER_ID));
    }

    public void upgrade() {}

    public void use(AbstractPlayer p, AbstractMonster m) {}
}
