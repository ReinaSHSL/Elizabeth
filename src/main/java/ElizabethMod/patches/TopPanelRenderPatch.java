package ElizabethMod.patches;

import ElizabethMod.ui.buttons.PersonaFusionButton;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.ui.panels.TopPanel;

public class TopPanelRenderPatch {
    @SpirePatch(cls="com.megacrit.cardcrawl.ui.panels.TopPanel", method = "renderDeckIcon")
    public static class RenderDeckIcon{
        public static void Postfix(TopPanel __instance, SpriteBatch sb) {
            PersonaFusionButton.renderPersonaFusionButton(sb);
        }
    }
}
