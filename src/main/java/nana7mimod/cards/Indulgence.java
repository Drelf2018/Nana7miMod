package nana7mimod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.SilencePower;

public class Indulgence extends Base {
    public static final String ID = ModHelper.id(Indulgence.class);

    public Indulgence() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF_AND_ENEMY);
        this.magicNumber = this.baseMagicNumber = 30;
        this.selfRetain = true;
        this.exhaust = true;
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(20);
            this.textureImg = ModHelper.cards(CardType.SKILL, ModHelper.PRIFIX + "Indulgence_50");
            this.loadCardImage(this.textureImg);
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (!m.isDeadOrEscaped() && m.hasPower(SilencePower.POWER_ID)) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower silence = m.getPower(SilencePower.POWER_ID);
        if (silence != null && silence instanceof SilencePower) {
            p.gainGold(magicNumber);
            for (int i = 0; i < magicNumber; i++)
                AbstractDungeon.effectList.add(new GainPennyEffect(p, m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY, true));
        }
    }
}
