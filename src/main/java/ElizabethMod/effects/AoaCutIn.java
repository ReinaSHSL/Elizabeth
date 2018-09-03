package ElizabethMod.effects;

import ElizabethMod.tools.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class AoaCutIn extends AbstractGameEffect {
    private float x = -1920;
    private float vX2 = 10000;
    private float vX = 19000;

    public AoaCutIn() {
        this.renderBehind = false;
        this.duration = 1.0F;
    }

    @Override
    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0f) {
            this.isDone = true;
        }
        if (this.duration > 0.8) {
            this.x += this.vX2 * Gdx.graphics.getDeltaTime();
        }
        if (this.duration < 0.3) {
            this.x += this.vX * Gdx.graphics.getDeltaTime();
        }

    }

    @Override
    public void render(SpriteBatch aoa) {
        float y = Settings.HEIGHT - 483F * Settings.scale;
        Texture cutIn = TextureLoader.getTexture("ElizabethImgs/char/aoaCutIn.png");
        aoa.setColor(Color.WHITE.cpy());
        aoa.draw(cutIn, x, y,
                0.0f, 0.0f, 1920F * Settings.scale, 483 * Settings.scale, Settings.scale, Settings.scale,
                0.0f, 0, 0, 1920, 483, false, false);
    }
}
