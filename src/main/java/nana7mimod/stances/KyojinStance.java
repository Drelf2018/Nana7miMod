package nana7mimod.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect;
import nana7mimod.helpers.ModHelper;

public class KyojinStance extends AbstractStance {
    public interface KyojinHandler {
        void Kyojinka();

        void Kaijo();
    }

    public static Texture scale(FileTextureData data, float scale) {
        Pixmap originalPixmap = new Pixmap(data.getFileHandle());
        int w = originalPixmap.getWidth(), h = originalPixmap.getHeight();

        Pixmap scaledPixmap = new Pixmap((int) (scale * w), (int) (scale * h), originalPixmap.getFormat());
        scaledPixmap.drawPixmap(originalPixmap, 0, 0, w, h, 0, 0, (int) (scale * w), (int) (scale * h));

        return new Texture(scaledPixmap);
    }

    public static final String STANCE_ID = ModHelper.id("Kyojin");
    private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    private static long sfxId = -1L;

    private int maxHealth;
    private int currentHealth;
    private int kyojinHealth;

    public KyojinStance(int kyojinHealth) {
        this.ID = STANCE_ID;
        this.name = stanceString.NAME;
        this.kyojinHealth = kyojinHealth;
        updateDescription();
    }

    @Override
    public float atDamageGive(float damage, DamageType type) {
        if (type == DamageType.NORMAL)
            return damage * 2.0F;
        return damage;
    }

    @Override
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.05F;
                AbstractDungeon.effectsQueue.add(new WrathParticleEffect());
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.3F, 0.4F);
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Wrath"));
        }
    }

    public void updateDescription() {
        description = stanceString.DESCRIPTION[0];
    }

    @Override
    public void onEnterStance() {
        if (sfxId != -1L) {
            stopIdleSfx();
        }

        maxHealth = AbstractDungeon.player.maxHealth;
        currentHealth = AbstractDungeon.player.currentHealth;

        AbstractDungeon.player.maxHealth = AbstractDungeon.player.currentHealth = kyojinHealth;
        AbstractDungeon.player.healthBarUpdatedEvent();

        CardCrawlGame.sound.play("STANCE_ENTER_WRATH");
        sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_WRATH");
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SCARLET, true));
        AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, "Wrath"));
    }

    public void onRemove() {
        if (maxHealth == 0)
            return;
        AbstractDungeon.player.currentHealth = 0;
        AbstractDungeon.player.maxHealth = maxHealth;
        AbstractDungeon.player.heal(currentHealth, true);
        if (AbstractDungeon.player instanceof KyojinHandler) {
            ((KyojinHandler) AbstractDungeon.player).Kaijo();
        }
        maxHealth = 0;
    }

    @Override
    public void onExitStance() {
        stopIdleSfx();
    }

    @Override
    public void stopIdleSfx() {
        onRemove();
        if (sfxId != -1L) {
            CardCrawlGame.sound.stop("STANCE_LOOP_WRATH", sfxId);
            sfxId = -1L;
        }
    }
}
