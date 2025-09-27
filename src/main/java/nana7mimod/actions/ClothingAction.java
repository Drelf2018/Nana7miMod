package nana7mimod.actions;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class ClothingAction extends AbstractGameAction {
    private Texture clothes;

    public ClothingAction(Texture clothes, float duration) {
        this.actionType = ActionType.WAIT;
        this.duration = this.startDuration = duration;
        this.clothes = clothes;
    }

    public ClothingAction(Texture clothes) {
        this(clothes, 0.016F);
    }

    public ClothingAction(String path, float duration) {
        this(ImageMaster.loadImage(path), duration);
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
