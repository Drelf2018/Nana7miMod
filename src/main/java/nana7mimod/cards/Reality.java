package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;

public class Reality extends Base {
    public static final String ID = ModHelper.id(Reality.class);

    public Reality() {
        super(ID, CardCost.C1, CardType.STATUS, CardTarget.SELF);
        this.purgeOnUse = true;
    }

    public void upgrade() {}

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RemoveDebuffsAction(p));
    }
}
