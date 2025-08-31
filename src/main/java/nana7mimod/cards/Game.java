package nana7mimod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import nana7mimod.helpers.ModHelper;

public class Game extends Base {
    public static final String ID = ModHelper.id(Game.class);

    public static final int CORRECT = ModHelper.countGames(true);

    public static final int INCORRECT = ModHelper.countGames(false);

    private boolean incorrect;

    private void init(boolean incorrect) {
        this.incorrect = incorrect;
        this.damage = this.baseDamage = 10;
    }

    public Game() {
        super(ID, CardCost.CN, CardType.STATUS, CardTarget.NONE);
        this.init(true);
    }

    public Game(String name, String img) {
        super(ID, name, ModHelper.cards(CardType.STATUS, img));
        this.init(img.contains("incorrect"));
    }

    public static Game Correct(int index) {
        return new Game(strings(ID).EXTENDED_DESCRIPTION[index], "game/correct/" + index);
    }

    public static Game Incorrect(int index) {
        return new Game(strings(ID).EXTENDED_DESCRIPTION[CORRECT + index + 1], "game/incorrect/" + index);
    }

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        if (incorrect)
            addToBot(new DamageAction(p, new DamageInfo(p, damage, DamageType.NORMAL), AttackEffect.BLUNT_LIGHT));
        else {
            isMultiDamage = true;
            addToBot(new SFXAction("ATTACK_HEAVY"));
            addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
            addToBot(new DamageAllEnemiesAction(p, damage, DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(5);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }
}
