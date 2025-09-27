package nana7mimod.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class KyojinkaSmokeEffect extends com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect {
    private float x;
    private float y;

    public KyojinkaSmokeEffect(float x, float y) {
        super(x, y);
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        if (this.duration == 0.2F) {
            CardCrawlGame.sound.play("ATTACK_WHIFF_2");

            for (int i = 0; i < 90; ++i) {
                Color color = new Color(0.0F, 0.0F, 0.0F, 1.0F);
                color.r = MathUtils.random(245.0F / 255.0F, 253.0F / 255.0F);
                color.g = MathUtils.random(191.0F / 255.0F, 232.0F / 255.0F);
                color.b = MathUtils.random(8.0F / 255.0F, 85.0F / 255.0F);
                AbstractDungeon.effectsQueue.add(new SmokeBlurEffect(this.x, this.y, color));
            }
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            CardCrawlGame.sound.play("APPEAR");
            this.isDone = true;
        }
    }
}
