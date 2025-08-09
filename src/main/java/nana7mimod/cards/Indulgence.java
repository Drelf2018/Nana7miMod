package nana7mimod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import nana7mimod.helpers.ModHelper;

public class Indulgence extends Base {
    public static final String ID = ModHelper.id(Indulgence.class);

    public Indulgence() {
        super(ID, CardCost.C2, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 30;
        this.exhaust = true;
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(20);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower strength = m.getPower(StrengthPower.POWER_ID);
        if (strength != null && strength.amount == -999) {
            p.gainGold(magicNumber);
            for (int i = 0; i < magicNumber; i++)
                AbstractDungeon.effectList.add(new GainPennyEffect(p, m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY, true));
        }
    }
}
