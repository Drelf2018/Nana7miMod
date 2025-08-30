package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import nana7mimod.helpers.ModHelper;

public class Idle extends Base {
    public static final String ID = ModHelper.id(Idle.class);

    public Idle() {
        super(ID, CardCost.C0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.magicNumber = this.baseMagicNumber = 10;
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(-5);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                p.loseGold(magicNumber);
                for (int i = 0; i < magicNumber; ++i) {
                    AbstractDungeon.effectList.add(new GainPennyEffect(mo, p.hb.cX, p.hb.cY, mo.hb.cX, mo.hb.cY, false));
                }
                addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, 99, false)));
            }
        }
    }
}
