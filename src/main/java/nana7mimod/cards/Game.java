package nana7mimod.cards;

import java.util.Map;
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
import basemod.BaseMod;
import nana7mimod.helpers.ModHelper;

public class Game extends Base {
    public static class GameStrings {
        public Map<String, String> PLAYED;
        public Map<String, String> UNPLAYED;
    }

    public static final String ID = ModHelper.id(Game.class);

    public static final GameStrings GAMES = BaseMod.gson.fromJson(ModHelper.games(), GameStrings.class);

    public static final String[] PLAYED = GAMES.PLAYED.keySet().toArray(new String[0]);

    public static final String[] UNPLAYED = GAMES.UNPLAYED.keySet().toArray(new String[0]);

    public Game() {
        super(ID, CardCost.CN, CardType.STATUS, CardTarget.NONE);
        this.damage = this.baseDamage = 10;
    }

    public Game(String name, String img, CardTarget target) {
        super(ID, name, ModHelper.cards("game", img), target);
        this.damage = this.baseDamage = 10;
        this.isMultiDamage = target == CardTarget.ALL_ENEMY;
    }

    public static Game Played() {
        String img = PLAYED[AbstractDungeon.cardRandomRng.random(PLAYED.length - 1)];
        return new Game(GAMES.PLAYED.get(img), img, CardTarget.ALL_ENEMY);
    }

    public static Game Unplayed() {
        String img = UNPLAYED[AbstractDungeon.cardRandomRng.random(UNPLAYED.length - 1)];
        return new Game(GAMES.UNPLAYED.get(img), img, CardTarget.SELF);
    }

    public static Game SlayTheSpire() {
        return new Game("杀戮尖塔", "杀戮尖塔", CardTarget.ALL_ENEMY);
    }

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        if (isMultiDamage) {
            addToBot(new SFXAction("ATTACK_HEAVY"));
            addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
            addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageType.NORMAL, AttackEffect.NONE));
        } else
            addToBot(new DamageAction(p, new DamageInfo(p, damage), AttackEffect.BLUNT_LIGHT));
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
