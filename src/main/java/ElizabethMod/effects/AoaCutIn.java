package ElizabethMod.effects;

import ElizabethMod.tools.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class AoaCutIn extends AbstractGameEffect {

    public AoaCutIn() {
        this.renderBehind = false;
        this.duration = 0.5F;
    }

    @Override
    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0f) {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch aoa) {
        float y = Settings.HEIGHT - 600F * Settings.scale;
        float x = 0;
        Texture cutIn = TextureLoader.getTexture("ElizabethImgs/char/aoaCutIn.png");
        aoa.setColor(Color.WHITE.cpy());
        aoa.draw(cutIn, x, y,
                0.0f, 0.0f, 1920F * Settings.scale, 842 * Settings.scale, Settings.scale, Settings.scale,
                0.0f, 0, 0, 1920, 842, false, false);
    }
}
