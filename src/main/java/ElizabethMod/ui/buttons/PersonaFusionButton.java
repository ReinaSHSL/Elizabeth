package ElizabethMod.ui.buttons;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.patches.ScreenStatePatch;
import ElizabethMod.tools.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

public class PersonaFusionButton {
    private static float rotation = 0f;
    private static float yPos = Settings.HEIGHT - (64f * Settings.scale);
    private static float tipYPos = Settings.HEIGHT - (120.0f * Settings.scale);
    private static float xPos = Settings.WIDTH - (425f * Settings.scale);
    private static float padding = (10.0f * Settings.scale) * 4f;
    private static float size = 64f * Settings.scale;
    private static boolean isEnabled = true;
    private static Hitbox hb = new Hitbox(xPos - padding, yPos , 64f * Settings.scale, 64f * Settings.scale);

    public static void renderPersonaFusionButton(SpriteBatch sb) {
        sb.setColor(Color.WHITE);

        Texture texture = TextureLoader.getTexture("ElizabethImgs/ui/buttons/personaFusionIcon.png");

        sb.draw(texture, xPos - padding, yPos, size / 2f, size / 2f, size, size, 1f, 1f, rotation, 0, 0, 64, 64, false, false);
        if (hb.hovered) {
            sb.setBlendFunction(770, 1);
            sb.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f));
            sb.draw(texture, xPos - padding, yPos, size / 2f, size / 2f, size, size, 1f, 1f, rotation, 0, 0, 64, 64, false, false);
            sb.setBlendFunction(770, 771);
            renderToolTip(sb);
        }
        hb.render(sb);
    }

    public static void updatePersonaFusionButton() {
        hb.update();

        if(hb.justHovered) {
            CardCrawlGame.sound.play("UI_HOVER");
        }

        boolean clickedPersonaFusionButton = hb.hovered && InputHelper.justClickedLeft;
        if(clickedPersonaFusionButton && !CardCrawlGame.isPopupOpen) {
            if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
                AbstractDungeon.closeCurrentScreen();
                ElizabethModInitializer.personaFusionScreen.open();
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
            }
            else if (!AbstractDungeon.isScreenUp) {
                ElizabethModInitializer.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == ScreenStatePatch.PERSONA_FUSION_SCREEN) {
                if (ElizabethModInitializer.personaFusionScreen.openedDuringReward) {
                    ElizabethModInitializer.personaFusionScreen.openedDuringReward = false;
                    AbstractDungeon.combatRewardScreen.reopen();
                }
                else {
                    AbstractDungeon.closeCurrentScreen();
                    CardCrawlGame.sound.play("DECK_CLOSE");
                }
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DEATH) {
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.DEATH;
                AbstractDungeon.deathScreen.hide();
                ElizabethModInitializer.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.BOSS_REWARD;
                AbstractDungeon.bossRelicScreen.hide();
                ElizabethModInitializer.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SHOP) {
                AbstractDungeon.overlayMenu.cancelButton.hide();
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.SHOP;
                ElizabethModInitializer.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.MAP;
                AbstractDungeon.closeCurrentScreen();
                ElizabethModInitializer.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SETTINGS) {
                if (AbstractDungeon.previousScreen != null) {
                    AbstractDungeon.screenSwap = true;
                }
                AbstractDungeon.closeCurrentScreen();
                ElizabethModInitializer.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.INPUT_SETTINGS && clickedPersonaFusionButton) {
                if (AbstractDungeon.previousScreen != null) {
                    AbstractDungeon.screenSwap = true;
                }
                AbstractDungeon.closeCurrentScreen();
                ElizabethModInitializer.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD) {
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.CARD_REWARD;
                AbstractDungeon.dynamicBanner.hide();
                ElizabethModInitializer.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.GRID;
                AbstractDungeon.gridSelectScreen.hide();
                ElizabethModInitializer.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT) {
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.HAND_SELECT;
                ElizabethModInitializer.personaFusionScreen.open();
            }
            InputHelper.justClickedLeft = false;
        }
    }

    public static void renderToolTip(SpriteBatch sb){
        sb.setColor(Color.CYAN);
        TipHelper.renderGenericTip(xPos - padding, tipYPos, "Velvet Room", "Fuse and purchase Persona.");
    }

    public static void onClick() {
        if(!AbstractDungeon.isScreenUp) {
            ElizabethModInitializer.personaFusionScreen.open();
        } else {
            AbstractDungeon.closeCurrentScreen();
            ElizabethModInitializer.personaFusionScreen.close();
        }
    }
}

