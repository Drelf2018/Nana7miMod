package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import nana7mimod.helpers.ModHelper;

public class Scared extends Base {
    public static final String ID = ModHelper.id(Scared.class);

    public Scared() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.block = this.baseBlock = 15;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(6);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, magicNumber, false)));
        addToBot(new GainBlockAction(p, p, block));
    }
}
