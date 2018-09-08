package ElizabethMod.ui.screens;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.patches.AbstractCardPatch;
import ElizabethMod.patches.ScreenStatePatch;
import ElizabethMod.ui.buttons.PersonaFusionButton;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import org.lwjgl.Sys;

import java.lang.reflect.Field;

public class CompendiumScreen {
    public static boolean compendiumList;

    public void open() {
        AbstractDungeon.previousScreen = ScreenStatePatch.PERSONA_FUSION_SCREEN;
        AbstractDungeon.screen = ScreenStatePatch.PERSONA_FUSION_SCREEN;
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : ElizabethModInitializer.compendium) {
            AbstractCardPatch.Field.isCompendium.set(c, true);
            tmp.addToTop(c);
        }
        if (tmp.size() != 0) {
            compendiumList = true;
            AbstractDungeon.gridSelectScreen.open(tmp, 999, "Purchase previously owned Persona", false);
            PersonaFusionScreen.personaChange = false;
        }
    }

    public void close() {
        AbstractDungeon.overlayMenu.cancelButton.hide();
        if (MathUtils.randomBoolean()) {
            CardCrawlGame.sound.play("MAP_OPEN", 0.1f);
        } else {
            CardCrawlGame.sound.play("MAP_OPEN_2", 0.1f);
        }
    }

    public void update() {
        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0 && compendiumList && !PersonaFusionScreen.personaChange) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                c.unhover();
                c.stopGlowing();
                AbstractPersonaCard ca = (AbstractPersonaCard)c;
                if (AbstractDungeon.player.gold >= ca.goldValue) {
                    AbstractDungeon.player.loseGold(ca.goldValue);
                    AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(ca.makeStatEquivalentCopy(),
                            Settings.WIDTH / 2F * Settings.scale, Settings.HEIGHT / 2F * Settings.scale, false));
                }
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }
}
