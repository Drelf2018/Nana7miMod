package nana7mimod.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class ClothingAction extends AbstractGameAction {
    private Texture clothes;

    public ClothingAction(String path, float startDuration) {
        this.actionType = ActionType.WAIT;
        this.duration = this.startDuration = startDuration;
        if (Gdx.files.internal(path).exists())
            this.clothes = ImageMaster.loadImage(path);
    }

    public ClothingAction(String path) {
        this(path, 0.016F);
    }

    public void update() {
        if (duration == startDuration && clothes != null) {
            AbstractDungeon.player.img = clothes;
        }
        tickDuration();
    }
}
