package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import nana7mimod.helpers.ModHelper;
import nana7mimod.powers.SilencePower;

public class Silence extends Base {
    public static final String ID = ModHelper.id(Silence.class);

    public Silence() {
        super(ID, CardCost.C1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        this.isEthereal = true;
    }

    public void upgrade() {
        if (!upgraded) {
            isEthereal = false;
            upgradeName();
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (m.getIntentBaseDmg() >= 0)
                    addToBot(new ApplyPowerAction(m, m, new SilencePower(m, 1)));
                else {
                    UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("OpeningAction");
                    AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, uiStrings.TEXT[0], true));
                }
                isDone = true;
            }
        });
    }
}
