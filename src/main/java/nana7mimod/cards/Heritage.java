package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.GetHeritagePower;
import nana7mimod.powers.HeritagePower;

public class Heritage extends Base {
    public static final String ID = ModHelper.id(Heritage.class);

    public Heritage() {
        super(ID, CardCost.C1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 10;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(5);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new GetHeritagePower(p, 3)));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters)
            addToBot(new ApplyPowerAction(mo, p, new HeritagePower(mo, magicNumber)));
    }
}
