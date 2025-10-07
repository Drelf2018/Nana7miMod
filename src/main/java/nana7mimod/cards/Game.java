package nana7mimod.cards;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
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
import nana7mimod.helpers.GameStrings;
import nana7mimod.helpers.ModHelper;

public class Game extends Base {
    public static final String ID = ModHelper.id(Game.class);

    public static final Map<String, GameStrings> GAMES = GameStrings.load();

    public static final ArrayList<String> INCORRECT;

    public static final ArrayList<String> CORRECT;

    static {
        CORRECT = GAMES.entrySet().stream().filter(entry -> Boolean.TRUE.equals(entry.getValue().PLAYED)).map(Map.Entry::getKey)
                .collect(Collectors.toCollection(ArrayList::new));
        INCORRECT = GAMES.entrySet().stream().filter(entry -> Boolean.FALSE.equals(entry.getValue().PLAYED)).map(Map.Entry::getKey)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean incorrect;

    public Game() {
        super(ID, CardCost.CN, CardType.STATUS, CardTarget.NONE);
        this.damage = this.baseDamage = 10;
    }

    public Game(String name, Boolean incorrect) {
        super(ID, GAMES.get(name).NAME, ModHelper.cards("game", name));
        this.damage = this.baseDamage = 10;
        this.incorrect = incorrect;
    }

    public static Game Correct(int index) {
        return new Game(CORRECT.get(index), false);
    }

    public static Game Incorrect(int index) {
        return new Game(INCORRECT.get(index), true);
    }

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        if (incorrect)
            addToBot(new DamageAction(p, new DamageInfo(p, damage), AttackEffect.BLUNT_LIGHT));
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
