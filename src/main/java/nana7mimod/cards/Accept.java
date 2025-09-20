package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.ATFieldPower;

public class Accept extends Base {
    public static final String ID = ModHelper.id(Accept.class);

    public Accept() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
    }

    @Override
    public void triggerOnGlowCheck() {
        AbstractPower p = ATFieldPower.from(AbstractDungeon.player);
        if (p != null && p.amount > cost)
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        else
            glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(cost - 1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ATFieldPower.setAmount(p, amount -> {
            if (amount <= 0)
                return amount;
            addToBot(new GainEnergyAction(amount));
            return 0;
        });
    }
}
