package ElizabethMod.effects;

import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;

public class InvertedCleaveEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float vX;
    private static final float FADE_IN_TIME = 0.05f;
    private static final float FADE_OUT_TIME = 0.4f;
    private float fadeInTimer;
    private float fadeOutTimer;
    private float stallTimer;
    private TextureAtlas.AtlasRegion img;

    public InvertedCleaveEffect(final boolean usedByMonster) {
        this.fadeInTimer = 0.05f;
        this.fadeOutTimer = 0.4f;
        this.img = ImageMaster.vfxAtlas.findRegion("combat/cleave");
        this.color = new Color(4.0f, 61.0f, 153.0f, 0.3f);
        this.x = Settings.WIDTH * 0.3f - this.img.packedWidth / 2.0f;
        this.y = AbstractDungeon.floorY + 100.0f * Settings.scale - this.img.packedHeight / 2.0f;
        this.vX = -100.0f * Settings.scale;
        this.stallTimer = MathUtils.random(0.0f, 0.2f);
        this.scale = 1.2f * Settings.scale;
        this.rotation = MathUtils.random(-5.0f, 1.0f);
    }

    public InvertedCleaveEffect() {
        this.fadeInTimer = 0.05f;
        this.fadeOutTimer = 0.4f;
        this.img = ImageMaster.vfxAtlas.findRegion("combat/cleave");
        this.color = new Color(1.0f, 1.0f, 1.0f, 0.0f);
        this.x = Settings.WIDTH * 0.8f - this.img.packedWidth / 2.0f;
        this.y = AbstractDungeon.floorY + 100.0f * Settings.scale - this.img.packedHeight / 2.0f;
        this.vX = -100.0f * Settings.scale;
        this.stallTimer = MathUtils.random(0.0f, 0.2f);
        this.scale = 1.2f * Settings.scale;
        this.rotation = MathUtils.random(-5.0f, 1.0f);
    }

    @Override
    public void update() {
        if (this.stallTimer > 0.0f) {
            this.stallTimer -= Gdx.graphics.getDeltaTime();
            return;
        }
        this.x += this.vX * Gdx.graphics.getDeltaTime();
        this.rotation += MathUtils.random(-0.5f, 0.5f);
        this.scale += 0.005f * Settings.scale;
        if (this.fadeInTimer != 0.0f) {
            this.fadeInTimer -= Gdx.graphics.getDeltaTime();
            if (this.fadeInTimer < 0.0f) {
                this.fadeInTimer = 0.0f;
            }
            this.color.a = Interpolation.fade.apply(1.0f, 0.0f, this.fadeInTimer / 0.05f);
        } else if (this.fadeOutTimer != 0.0f) {
            this.fadeOutTimer -= Gdx.graphics.getDeltaTime();
            if (this.fadeOutTimer < 0.0f) {
                this.fadeOutTimer = 0.0f;
            }
            this.color.a = Interpolation.pow2.apply(0.0f, 1.0f, this.fadeOutTimer / 0.4f);
        } else {
            this.isDone = true;
        }
    }

    @Override
    public void render(final SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0f, this.img.packedHeight / 2.0f, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0f, this.img.packedHeight / 2.0f, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
        sb.setBlendFunction(770, 771);
    }
}