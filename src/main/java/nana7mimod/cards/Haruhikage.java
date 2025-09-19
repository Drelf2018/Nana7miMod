package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import nana7mimod.helpers.ModHelper;

public class Haruhikage extends Base {
    public static final String ID = ModHelper.id(Haruhikage.class);

    public Haruhikage() {
        super(ID, CardCost.C2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(cost - 1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int maxHP = 0;
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters)
            if (mo.currentHealth > maxHP)
                maxHP = mo.currentHealth;
        if (m.currentHealth < maxHP) {
            addToBot(new WaitAction(0.8F));
            addToBot(new EscapeAction(m));
        } else {
            String ext = strings(ID).EXTENDED_DESCRIPTION[0];
            AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, ext, true));
        }
    }
}
