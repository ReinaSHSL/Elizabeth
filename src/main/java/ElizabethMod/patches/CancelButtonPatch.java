package ElizabethMod.patches;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.character.Elizabeth;
import ElizabethMod.ui.screens.CompendiumScreen;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.buttons.CancelButton;


public class CancelButtonPatch {

    @SpirePatch(clz = CancelButton.class, method = "hide")
    public static class CancelButtonHide {
        public static void Postfix(CancelButton __instance) {
            if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID && AbstractDungeon.previousScreen == ScreenStatePatch.PERSONA_FUSION_SCREEN) {
                ElizabethModInitializer.personaFusionScreen.open(false);
            }
            if (AbstractDungeon.screen == ScreenStatePatch.COMPENDIUM_SCREEN && AbstractDungeon.previousScreen == ScreenStatePatch.PERSONA_FUSION_SCREEN) {
                ElizabethModInitializer.personaFusionScreen.open(false);
                CompendiumScreen.compendiumList = false;
            }
        }
    }

}
