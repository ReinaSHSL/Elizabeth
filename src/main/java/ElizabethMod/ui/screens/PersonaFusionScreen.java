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
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.Strike_Blue;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import org.lwjgl.Sys;

import java.util.ArrayList;

public class PersonaFusionScreen {

    private ArrayList<Hitbox> hbs = new ArrayList<Hitbox>();
    private boolean justClicked = false;
    private float yScale;
    public boolean openedDuringReward;
    private static Hitbox personaOne;
    private static Hitbox personaTwo;
    private static Hitbox personaResult;
    private static AbstractCard personaOneCard;
    private static AbstractCard personaTwoCard;
    private static AbstractCard personaResultCard;
    private boolean personaChange = false;
    private boolean changePersonaOne = false;
    private boolean changePersonaTwo = false;

    public PersonaFusionScreen() {
    }

    public void open() {
        personaOneCard = new Strike_Blue().makeStatEquivalentCopy();
        personaTwoCard = new Strike_Blue().makeStatEquivalentCopy();
        personaResultCard = new Strike_Blue().makeStatEquivalentCopy();
        personaOneCard.drawScale = 1.0f;
        personaOneCard.current_x = Settings.WIDTH / 4F * Settings.scale;
        personaOneCard.current_y = (Settings.HEIGHT / 2F - 200F) * Settings.scale ;
        personaOne = new Hitbox(personaOneCard.current_x - (AbstractCard.IMG_WIDTH/2), personaOneCard.current_y - (AbstractCard.IMG_HEIGHT/2),
                AbstractCard.IMG_WIDTH, AbstractCard.IMG_HEIGHT);
        personaTwoCard.drawScale = 1.0f;
        personaTwoCard.current_x = Settings.WIDTH / 1.35F * Settings.scale;
        personaTwoCard.current_y = (Settings.HEIGHT / 2F - 200F) * Settings.scale ;
        personaTwo = new Hitbox(personaTwoCard.current_x - (AbstractCard.IMG_WIDTH/2), personaTwoCard.current_y - (AbstractCard.IMG_HEIGHT/2),
                AbstractCard.IMG_WIDTH, AbstractCard.IMG_HEIGHT);
        personaResultCard.drawScale = 1.0f;
        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale ;
        personaResult = new Hitbox(personaResultCard.current_x - (AbstractCard.IMG_WIDTH/2), personaResultCard.current_y - (AbstractCard.IMG_HEIGHT/2),
                AbstractCard.IMG_WIDTH, AbstractCard.IMG_HEIGHT);
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
        personaOneCard.render(sb);
        personaOne.render(sb);
        personaTwoCard.render(sb);
        personaTwo.render(sb);
        personaResultCard.render(sb);
        personaResult.render(sb);
    }

    public void update() {
        personaOne.update();
        personaTwo.update();
        if(InputHelper.justClickedLeft) {
            justClicked = true;
        }
        if (personaOne.hovered) {
            updateCardPreview(personaOneCard);
        }
        if (personaTwo.hovered) {
            updateCardPreview(personaTwoCard);
        }
        if (personaResult.hovered) {
            updateCardPreview(personaResultCard);
        }
        if(personaOne.hovered && InputHelper.justClickedLeft) {
            changePersonaOne = true;
            openPersonaList();
        }
        if (personaTwo.hovered && InputHelper.justClickedLeft) {
            changePersonaTwo = true;
            openPersonaList();
        }
        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0 && this.personaChange) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                c.unhover();
                if (changePersonaOne) {
                    personaOneCard = c.makeStatEquivalentCopy();
                    personaOneCard.drawScale = 1.0f;
                    personaOneCard.current_x = Settings.WIDTH / 4F * Settings.scale;
                    personaOneCard.current_y = (Settings.HEIGHT / 2F - 200F) * Settings.scale;
                    personaChange = false;
                    changePersonaOne = false;
                }
                if (changePersonaTwo) {
                    personaTwoCard = c.makeStatEquivalentCopy();
                    personaTwoCard.drawScale = 1.0f;
                    personaTwoCard.current_x = Settings.WIDTH / 1.35F * Settings.scale;
                    personaTwoCard.current_y = (Settings.HEIGHT / 2F - 200F) * Settings.scale ;
                    personaChange = false;
                    changePersonaTwo = false;
                }
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }

    private void updateCardPreview(AbstractCard c) {
    }

    private void openPersonaList() {
        AbstractDungeon.previousScreen = ScreenStatePatch.PERSONA_FUSION_SCREEN;
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : ElizabethModInitializer.arcanaList) {
            tmp.addToTop(c);
        }
        this.personaChange = true;
        AbstractDungeon.gridSelectScreen.open(tmp, 1, "Pick a Persona to fuse.", false);
    }
}
