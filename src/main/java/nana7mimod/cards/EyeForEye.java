package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import nana7mimod.helpers.CharacterHelper.Nana7mi;
import nana7mimod.helpers.ModHelper;
import nana7mimod.patches.DamageReceivedLastTurnPatch;

public class EyeForEye extends Base {
    public static final String ID = ModHelper.id(EyeForEye.class);

    public EyeForEye() {
        super(ID, CardCost.C1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 0;
    }

    @Override
    public void applyPowers() {
        baseDamage = DamageReceivedLastTurnPatch.damageReceivedLastTurn;
        super.applyPowers();
        rawDescription = strings(ID).DESCRIPTION + strings(ID).EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        rawDescription = strings(ID).DESCRIPTION + strings(ID).EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(cost - 1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null)
            addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Nana7mi.COLOR.cpy()), 0.3F));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        rawDescription = strings(ID).DESCRIPTION;
        initializeDescription();
    }
}
