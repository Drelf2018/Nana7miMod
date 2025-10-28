package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import nana7mimod.actions.CuishaLetsGOAction;
import nana7mimod.helpers.CharacterHelper;
import nana7mimod.helpers.ModHelper;

public class CuishaLetsGO extends Base {
	public static final String ID = ModHelper.id(CuishaLetsGO.class);

	public CuishaLetsGO() {
		super(ID, CardCost.C1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
		this.misc = 6;
		this.baseDamage = this.misc;
		this.magicNumber = this.baseMagicNumber = 4;
		this.exhaust = true;
	}

	@Override
	public void applyPowers() {
		baseDamage = misc;
		super.applyPowers();
		initializeDescription();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(2);
		}
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		playSound(ID);
		addToBot(new CuishaLetsGOAction(magicNumber, uuid));
		if (m != null)
			addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, CharacterHelper.NANA7MI_BLUE.cpy()), 0.3F));
		addToBot(new DamageAction(m, new DamageInfo(p, damage)));
	}
}
