package ElizabethMod.actions;

import ElizabethMod.effects.AoaCutIn;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AllOutAttackAttackAction extends AbstractGameAction {
    private SpriteBatch sb;

    public AllOutAttackAttackAction() {
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new AoaCutIn()));
            this.isDone = true;
        }
    }

}
