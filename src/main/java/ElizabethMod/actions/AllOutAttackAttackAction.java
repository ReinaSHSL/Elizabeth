package ElizabethMod.actions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.green.AllOutAttack;
import com.megacrit.cardcrawl.core.Settings;

public class AllOutAttackAttackAction extends AbstractGameAction {
    private SpriteBatch sb;

    public AllOutAttackAttackAction() {
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AllOutAttackAttack(sb);
        }
    }

    private void AllOutAttackAttack (SpriteBatch sb){
        float y = Settings.HEIGHT - 300F * Settings.scale;
        float x = Settings.WIDTH / 2F;
        Texture cutIn = new Texture("ElizabethImgs/char/aoaCutIn.png");
        sb.draw(cutIn, x, y,
                556.0f, 119.0f, 1112.0f, 238.0f, Settings.scale, Settings.scale,
                0.0f, 0, 0, 1112, 238, false, false);
    }
}
