package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import nana7mimod.helpers.ModHelper;

public class Depression extends Base {
    public static final String ID = ModHelper.id(Depression.class);

    public Depression() {
        super(ID, CardCost.C3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.block = this.baseBlock = 5;
        this.magicNumber = this.baseMagicNumber = 3;
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; ++i)
            addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new BufferPower(p, 1)));
    }
}
