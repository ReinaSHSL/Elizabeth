package ElizabethMod.ui.screens;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.cards.screencards.AllOutAttackNo;
import ElizabethMod.cards.screencards.AllOutAttackYes;
import ElizabethMod.cards.special.WildCard;
import ElizabethMod.patches.ScreenStatePatch;
import ElizabethMod.tools.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
    private float yScale;
    public boolean openedDuringReward;
    private static Hitbox personaOne;
    private static Hitbox personaTwo;

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
        Texture velvetRoom = TextureLoader.getTexture("ElizabethImgs/ui/screens/velvetRoom.png");
        sb.setColor(Color.WHITE);
        yScale = MathHelper.scaleLerpSnap(yScale, 1.0f);
        sb.draw(velvetRoom, 0, 0);
        renderCardPreview(sb);
        justClicked = false;
    }

    private void renderCardPreview(SpriteBatch sb) {
        AbstractCard personaOneCard = new AllOutAttackNo().makeStatEquivalentCopy();
        personaOneCard.drawScale = 1.0f;
        personaOneCard.render(sb);
        personaOneCard.current_x = Settings.WIDTH / 4F * Settings.scale;
        personaOneCard.current_y = (Settings.HEIGHT / 2F - 200F) * Settings.scale ;
        personaOne = new Hitbox(personaOneCard.current_x - (AbstractCard.IMG_WIDTH/2), personaOneCard.current_y - (AbstractCard.IMG_HEIGHT/2),
                AbstractCard.IMG_WIDTH, AbstractCard.IMG_HEIGHT);

        personaOne.render(sb);
        AbstractCard personaTwoCard = new WildCard().makeStatEquivalentCopy();
        personaTwoCard.drawScale = 1.0f;
        personaTwoCard.render(sb);
        personaTwoCard.current_x = Settings.WIDTH / 1.35F * Settings.scale;
        personaTwoCard.current_y = (Settings.HEIGHT / 2F - 200F) * Settings.scale ;
        personaTwo = new Hitbox(personaTwoCard.current_x - (AbstractCard.IMG_WIDTH/2), personaTwoCard.current_y - (AbstractCard.IMG_HEIGHT/2),
                AbstractCard.IMG_WIDTH, AbstractCard.IMG_HEIGHT);
        personaTwo.render(sb);
    }

    public void update() {
        if(InputHelper.justClickedLeft) {
            justClicked = true;
        }

    }

    private void updateCardPreview() {
        if(personaOne.justHovered || personaTwo.justHovered) {
            System.out.println("Oof");
        }
    }
}
