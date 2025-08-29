package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.MonitorPower;

public class Monitor extends Base {
    public static final String ID = ModHelper.id(Monitor.class);

    public Monitor() {
        super(ID, CardCost.C0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            isInnate = true;
            upgradeName();
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new MonitorPower(m, magicNumber)));
        if (!p.hasPower(MonitorPower.POWER_ID))
            addToBot(new ApplyPowerAction(p, p, new MonitorPower(p, -1)));
    }
}
