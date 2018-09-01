package ElizabethMod.ui.screens;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.patches.ScreenStatePatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

import java.util.ArrayList;

public class PersonaFusionScreen {

    private ArrayList<Hitbox> hbs = new ArrayList<Hitbox>();
    private boolean justClicked = false;
    private boolean justClickedRight = false;
    private float completedAlpha = 0f;
    private float completedSin = 0f;
    private float yScale;

    public boolean openedDuringReward;

    public PersonaFusionScreen() {
    }

    public void open() {
        AbstractDungeon.player.releaseCard();
        AbstractDungeon.screen = ScreenStatePatch.PERSONA_FUSION_SCREEN;
        AbstractDungeon.overlayMenu.showBlackScreen();
        AbstractDungeon.overlayMenu.proceedButton.hide();
        AbstractDungeon.overlayMenu.cancelButton.show("Return");
        AbstractDungeon.isScreenUp = true;
        if (MathUtils.randomBoolean()) {
            CardCrawlGame.sound.play("MAP_OPEN", 0.1f);
        }
        else {
            CardCrawlGame.sound.play("MAP_OPEN_2", 0.1f);
        }

        hbs.clear();

        this.yScale = 0.0f;
    }

    public void close() {
        AbstractDungeon.overlayMenu.cancelButton.hide();
        if (MathUtils.randomBoolean()) {
            CardCrawlGame.sound.play("MAP_OPEN", 0.1f);
        }
        else {
            CardCrawlGame.sound.play("MAP_OPEN_2", 0.1f);
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        yScale = MathHelper.scaleLerpSnap(yScale, 1.0f);
        this.renderBanner(sb);
        justClicked = false;
        justClickedRight = false;
    }

    public void update() {
        if(InputHelper.justClickedLeft) {
            justClicked = true;
        }

    }

    public void renderBanner(SpriteBatch sb){
        float y = Settings.HEIGHT - 280.0f * Settings.scale;

        sb.setColor(Color.WHITE.cpy());
        sb.draw(ImageMaster.VICTORY_BANNER, Settings.WIDTH / 2.0f - 556.0f, y - 119.0f,
                556.0f, 119.0f, 1112.0f, 238.0f, Settings.scale, Settings.scale,
                0.0f, 0, 0, 1112, 238, false, false);
        FontHelper.renderFontCentered(sb, FontHelper.bannerFont, "Velvet Room", Settings.WIDTH / 2.0f,
                y + 22.0f * Settings.scale, Color.WHITE, 1f);
    }
}
