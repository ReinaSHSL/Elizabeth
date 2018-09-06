package ElizabethMod.patches;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.ui.buttons.PersonaFusionButton;
import ElizabethMod.ui.screens.PersonaFusionScreen;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import com.megacrit.cardcrawl.ui.buttons.CancelButton;

@SpirePatch(clz = CancelButton.class, method = "hide")
public class CancelButtonPatch {

    @SpirePostfixPatch
    public static void Postfix(CancelButton __instance) {
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID && AbstractDungeon.previousScreen == ScreenStatePatch.PERSONA_FUSION_SCREEN) {
            ElizabethModInitializer.personaFusionScreen.open();
        }
    }
}
