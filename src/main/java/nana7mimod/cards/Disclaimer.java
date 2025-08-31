package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import nana7mimod.helpers.ModHelper;

public class Disclaimer extends Base {
    public static final String ID = ModHelper.id(Disclaimer.class);

    public Disclaimer() {
        super(ID, CardCost.CX, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
        this.cardsToPreview = new Armor();
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1)
            effect = energyOnUse;

        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }

        if (effect > 0 && !this.freeToPlayOnce)
            p.energy.use(EnergyPanel.totalCount);

        addToBot(new GainEnergyAction(magicNumber));
        Armor c = new Armor();
        c.setX(effect);
        addToBot(new MakeTempCardInHandAction(c));
    }
}
