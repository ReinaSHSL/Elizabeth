package ElizabethMod.ui.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

public class PersonaFusionButton {
    private static float rotation = 0f;
    private static float yPos = Settings.HEIGHT - (64f * Settings.scale);
    private static float tipYPos = Settings.HEIGHT - (120.0f * Settings.scale);
    private static float xPos = Settings.WIDTH - (256f * Settings.scale);
    private static float padding = (10.0f * Settings.scale) * 4f;
    private static float size = 64f * Settings.scale;
    private static boolean isEnabled = true;
    private static Hitbox hb = new Hitbox(xPos - padding, yPos , 64f * Settings.scale, 64f * Settings.scale);

    public static void renderPersonaFusionButton(SpriteBatch sb) {
        sb.setColor(Color.WHITE);

        Texture texture = new Texture("ElizabethImgs/ui/buttons/personaFusionIcon.png");

        if(AbstractDungeon.screen != AbstractDungeon.CurrentScreen.MAP) {
            sb.draw(texture, xPos - padding, yPos, size / 2f, size / 2f, size, size, 1f, 1f, rotation, 0, 0, 64, 64, false, false);
            if (hb.hovered) {
                sb.setBlendFunction(770, 1);
                sb.setColor(new Color(1.0f, 1.0f, 1.0f, 0.25f));
                sb.draw(texture, xPos - padding, yPos, size / 2f, size / 2f, size, size, 1f, 1f, rotation, 0, 0, 64, 64, false, false);
                sb.setBlendFunction(770, 771);


                renderToolTip(sb);
            }
        }
        hb.render(sb);
    }

    public static void updatePersonaFusionButton() {
        hb.update();

        if(hb.hovered) {
            rotation = MathHelper.angleLerpSnap(rotation, 15.0f);
        } else {
            rotation = MathHelper.angleLerpSnap(rotation, 0.0f);
        }

        if(AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) return;

        if(hb.justHovered) {
            CardCrawlGame.sound.play("UI_HOVER");
        }

        boolean clickedPersonaFusionButton = hb.hovered && InputHelper.justClickedLeft;
        if(clickedPersonaFusionButton && !CardCrawlGame.isPopupOpen) {
            if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
                AbstractDungeon.closeCurrentScreen();
                InfiniteSpire.personaFusionScreen.open();
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
            }
            else if (!AbstractDungeon.isScreenUp) {
                InfiniteSpire.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == ScreenStatePatch.QUEST_LOG_SCREEN) {
                if (InfiniteSpire.personaFusionScreen.openedDuringReward) {
                    InfiniteSpire.personaFusionScreen.openedDuringReward = false;
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
                InfiniteSpire.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.BOSS_REWARD;
                AbstractDungeon.bossRelicScreen.hide();
                InfiniteSpire.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SHOP) {
                AbstractDungeon.overlayMenu.cancelButton.hide();
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.SHOP;
                InfiniteSpire.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && !AbstractDungeon.dungeonMapScreen.dismissable) {
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.MAP;
                InfiniteSpire.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SETTINGS || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
                if (AbstractDungeon.previousScreen != null) {
                    AbstractDungeon.screenSwap = true;
                }
                AbstractDungeon.closeCurrentScreen();
                InfiniteSpire.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.INPUT_SETTINGS && clickedPersonaFusionButton) {
                if (AbstractDungeon.previousScreen != null) {
                    AbstractDungeon.screenSwap = true;
                }
                AbstractDungeon.closeCurrentScreen();
                InfiniteSpire.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD) {
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.CARD_REWARD;
                AbstractDungeon.dynamicBanner.hide();
                InfiniteSpire.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.GRID;
                AbstractDungeon.gridSelectScreen.hide();
                InfiniteSpire.personaFusionScreen.open();
            }
            else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT) {
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.HAND_SELECT;
                InfiniteSpire.personaFusionScreen.open();
            }
            InputHelper.justClickedLeft = false;
        }
    }

    public static void renderToolTip(SpriteBatch sb){
        sb.setColor(Color.CYAN);
        TipHelper.renderGenericTip(xPos - padding, tipYPos, "Quest Log", "View the currently active quests.");
    }

    public static void onClick() {
        if(!AbstractDungeon.isScreenUp) {
            InfiniteSpire.personaFusionScreen.open();
        } else {
            AbstractDungeon.closeCurrentScreen();
            InfiniteSpire.personaFusionScreen.close();
        }
    }
}

