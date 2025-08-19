package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import nana7mimod.helpers.ModHelper;
import nana7mimod.helpers.CharacterHelper.Nana7mi;
import nana7mimod.powers.CrazyPower;

public class Crazy extends Base {
    public static final String ID = ModHelper.id(Crazy.class);

    public Crazy() {
        super(ID, CardCost.C0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("RAGE"));
        addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Nana7mi.COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.0F));
        addToBot(new ApplyPowerAction(p, p, new CrazyPower(p, magicNumber)));
    }
}
