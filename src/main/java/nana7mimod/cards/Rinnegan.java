package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import nana7mimod.helpers.ModHelper;

public class Rinnegan extends Base {
    public static final String ID = ModHelper.id(Rinnegan.class);

    public Rinnegan() {
        super(ID, CardCost.C3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.damage = this.baseDamage = 15;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(5);
            upgradeMagicNumber(1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        addToBot(new WaitAction(0.8F));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageType.NORMAL)));
        addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
    }
}
