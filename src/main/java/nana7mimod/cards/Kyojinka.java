package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.actions.LightningAction;
import nana7mimod.helpers.ModHelper;
import nana7mimod.stances.KyojinStance;

public class Kyojinka extends Base {
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
        addToBot(new LightningAction(p, new DamageInfo(p, magicNumber, DamageType.HP_LOSS)));
        addToBot(new ChangeStanceAction(new KyojinStance(magicNumber)));
    }
}
