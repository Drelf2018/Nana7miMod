package nana7mimod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.actions.SilenceAction;

public class Silence extends Base {
    public static final String ID = ModHelper.id(Silence.class);

    public Silence() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        this.isEthereal = true;
    }

    public void upgrade() {
        if (!upgraded) {
            isEthereal = false;
            upgradeName();
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SilenceAction(m));
    }
}
