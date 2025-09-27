package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.actions.LightningAction;
import nana7mimod.effects.KyojinkaSmokeEffect;
import nana7mimod.helpers.ModHelper;
import nana7mimod.stances.KyojinStance;

public class Kyojinka extends Base {
    public interface KyojinHandler {
        void Kyojinka();

        void Kaijo();
    }

    public static final String ID = ModHelper.id(Kyojinka.class);

    public Kyojinka() {
        super(ID, CardCost.C1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 20;
    }

    public void upgrade() {
        if (!upgraded) {
            isInnate = true;
            upgradeName();
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.stance.ID != KyojinStance.STANCE_ID) {
            addToTop(new ChangeStanceAction(new KyojinStance(magicNumber)));

            if (p instanceof KyojinHandler)
                ((KyojinHandler) p).Kyojinka();
        }

        addToTop(new VFXAction(new KyojinkaSmokeEffect(p.hb.cX, p.hb.cY)));

        addToTop(new LightningAction(p, new DamageInfo(p, magicNumber, DamageType.HP_LOSS)));
    }
}
