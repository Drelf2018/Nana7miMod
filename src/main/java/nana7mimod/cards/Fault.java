package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nana7mimod.helpers.ModHelper;

public class Fault extends Base {
	public static final String ID = ModHelper.id(Fault.class);

	public Fault() {
		super(ID, CardCost.C1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
		this.damage = this.baseDamage = 10;
		this.magicNumber = this.baseMagicNumber = 1;
		this.cardsToPreview = new Fault2();
	}

	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(this.cost - 1);
		}
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new LoseHPAction(p, p, magicNumber));
		addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageType.NORMAL), AttackEffect.SLASH_DIAGONAL));
		addToBot(new MakeTempCardInHandAction(new Fault2(), 1));
	}
}
