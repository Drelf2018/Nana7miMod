package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.BackAttackPower;

public class Teleport extends Base {
    public static final String ID = ModHelper.id(Teleport.class);

    public Teleport() {
        super(ID, CardCost.C2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        this.magicNumber = this.baseMagicNumber = 3;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new ApplyPowerAction(p, p, new BackAttackPower(p, magicNumber)));
        addToTop(new RemoveSpecificPowerAction(p, p, "Surrounded"));
        addToTop(new AbstractGameAction() {
            @Override
            public void update() {
                if (2 * m.hb.cX < Settings.WIDTH) {
                    p.flipHorizontal = false;
                    p.movePosition(m.hb.x, p.hb.y);
                } else {
                    p.flipHorizontal = true;
                    p.movePosition(m.hb.x + m.hb.width, p.hb.y);
                }
                isDone = true;
            }
        });
        addToTop(new VFXAction(new FlickCoinEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.5F));
    }
}
