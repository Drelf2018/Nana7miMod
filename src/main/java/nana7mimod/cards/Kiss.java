package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.HexPower;
import com.megacrit.cardcrawl.powers.NoBlockPower;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.EnergyDownPower;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import nana7mimod.helpers.ModHelper;

public class Kiss extends Base {
    public static final String ID = ModHelper.id(Kiss.class);

    public Kiss() {
        super(ID, CardCost.C0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 5;
        this.purgeOnUse = true;
    }

    public void upgrade() {
        if (!upgraded) {
            isInnate = true;
            selfRetain = true;
            upgradeName();
            upgradeMagicNumber(-2);
            upgradeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        m = AbstractDungeon.getCurrRoom().monsters.monsters.get(0);
        String[] extended = strings(ID).EXTENDED_DESCRIPTION;
        addToBot(new TalkAction(m, extended[0], 6.0F, 6.0F));
        AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 6.0F, extended[1], true));
        addToBot(new SFXAction("MONSTER_COLLECTOR_DEBUFF"));
        addToBot(new VFXAction(new CollectorCurseEffect(p.hb.cX, p.hb.cY), 2.0F));
        addToBot(new ApplyPowerAction(p, m, new ConfusionPower(p)));
        addToBot(new ApplyPowerAction(p, m, new StrengthPower(p, -magicNumber)));
        addToBot(new ApplyPowerAction(p, m, new DexterityPower(p, -magicNumber)));
        addToBot(new ApplyPowerAction(p, m, new EnergyDownPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, m, new HexPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, m, new NoDrawPower(p)));
        addToBot(new ApplyPowerAction(p, m, new EntanglePower(p)));
        addToBot(new ApplyPowerAction(p, m, new DrawReductionPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, m, new NoBlockPower(p, magicNumber, true)));
        addToBot(new ApplyPowerAction(p, m, new VulnerablePower(p, magicNumber, true)));
        addToBot(new ApplyPowerAction(p, m, new FrailPower(p, magicNumber, true)));
        addToBot(new ApplyPowerAction(p, m, new WeakPower(p, magicNumber, true)));
        addToBot(new ApplyPowerAction(p, m, new ConstrictedPower(p, m, magicNumber)));
    }
}
