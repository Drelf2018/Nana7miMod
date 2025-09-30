package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import nana7mimod.helpers.CharacterHelper.Nana7mi;
import nana7mimod.powers.BleedingPower;
import nana7mimod.helpers.ModHelper;

public class Shark extends Base {
    public static final String ID = ModHelper.id(Shark.class);

    public Shark() {
        super(ID, CardCost.C2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 8;
        this.magicNumber = this.baseMagicNumber = 4;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null)
            addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Nana7mi.COLOR.cpy()), 0.3F));
        addToBot(new DamageAction(m, new DamageInfo(p, damage)));
        addToBot(new ApplyPowerAction(m, p, new BleedingPower(m, magicNumber)));
    }
}
