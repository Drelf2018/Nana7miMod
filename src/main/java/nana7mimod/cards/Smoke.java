package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.actions.SmokeAction;
import nana7mimod.helpers.ModHelper;

public class Smoke extends Base {
    public static final String ID = ModHelper.id(Smoke.class);

    public Smoke() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 41;
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(-12);
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SmokeAction());
        addToBot(new LoseHPAction(p, p, p.maxHealth * magicNumber / 100));
    }
}
