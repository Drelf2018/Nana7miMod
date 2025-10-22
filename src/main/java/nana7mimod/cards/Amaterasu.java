package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;
import nana7mimod.patches.AttackEffectPatch;

public class Amaterasu extends Base {
	public static final String ID = ModHelper.id(Amaterasu.class);

	public Amaterasu() {
		super(ID, CardCost.C2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
		this.damage = this.baseDamage = 25;
		this.magicNumber = this.baseMagicNumber = 4;
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(10);
		}
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		playSound(ID);
		addToBot(new LoseHPAction(p, p, magicNumber, AttackEffectPatch.BLACK_FIRE));
		addToBot(new DamageAction(m, new DamageInfo(p, damage), AttackEffectPatch.BLACK_FIRE));
	}
}
