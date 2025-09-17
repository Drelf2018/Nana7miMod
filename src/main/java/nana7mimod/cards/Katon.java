package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.ATFieldPower;

public class Katon extends Base {
    public static final String ID = ModHelper.id(Katon.class);

    public Katon() {
        super(ID, CardCost.C2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.damage = this.baseDamage = 3;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ATFieldPower.setAmount(p, amount -> {
            if (amount <= 0)
                return amount;
            for (int i = 0; i < amount; i++) {
                if (m != null)
                    addToBot(new VFXAction(new SearingBlowEffect(m.hb.cX, m.hb.cY, amount), 0.1F));
                addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageType.NORMAL)));
            }
            return 0;
        });
    }
}
