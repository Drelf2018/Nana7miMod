package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import nana7mimod.helpers.ModHelper;

public class Frail extends Base {
    public static final String ID = ModHelper.id(Frail.class);

    public Frail() {
        super(ID, CardCost.C2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.magicNumber = this.baseMagicNumber = 3;
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters)
            addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new FrailPower(p, magicNumber, false)));
    }
}
