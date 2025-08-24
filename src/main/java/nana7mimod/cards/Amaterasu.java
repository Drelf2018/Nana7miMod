package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import nana7mimod.actions.BlackFireAction;
import nana7mimod.helpers.ModHelper;

public class Amaterasu extends Base {
	public static final String ID = ModHelper.id(Amaterasu.class);

	public Amaterasu() {
		super(ID, CardCost.C2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
		this.damage = this.baseDamage = 25;
		this.magicNumber = this.baseMagicNumber = 5;
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(10);
		}
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new LoseHPAction(p, p, magicNumber, AbstractGameAction.AttackEffect.FIRE) {
			@Override
			public void update() {
				if (duration == 0.33F && target.currentHealth > 0) {
					FlashAtkImgEffect effect = new FlashAtkImgEffect(target.hb.cX, target.hb.cY, AttackEffect.FIRE);
					effect.img = BlackFireAction.vfxAtlas.findRegion("attack/black_fire");
					AbstractDungeon.effectList.add(effect);
					tickDuration();
				}
				super.update();
			}
		});
		addToBot(new BlackFireAction(m, new DamageInfo(p, damage, DamageType.NORMAL)));
	}
}
