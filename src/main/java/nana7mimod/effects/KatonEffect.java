package nana7mimod.effects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeDur;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeIntensity;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.RedFireballEffect;

public class KatonEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private int timesUpgraded;

    public KatonEffect(float x, float y, int timesUpgraded) {
        this.x = x;
        this.y = y;
        this.timesUpgraded = timesUpgraded;
        CardCrawlGame.screenShake.shake(ShakeIntensity.HIGH, ShakeDur.SHORT, true);
    }

    public void update() {
        CardCrawlGame.sound.playA("ATTACK_FIRE", 0.3F);
        float dst = 180.0F + (float) this.timesUpgraded * 3.0F;
        AbstractDungeon.effectsQueue.add(new RedFireballEffect(this.x - dst * Settings.scale, this.y, this.x + dst * Settings.scale,
                this.y - 50.0F * Settings.scale, this.timesUpgraded));
        this.isDone = true;
    }

    public void render(SpriteBatch sb) {}

    public void dispose() {}
}
