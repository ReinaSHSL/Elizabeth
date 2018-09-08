package ElizabethMod.patches;

import ElizabethMod.character.Elizabeth;
import ElizabethMod.ui.buttons.PersonaFusionButton;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.TopPanel;

public class TopPanelRenderPatch {

    @SpirePatch(clz = TopPanel.class, method = "renderDeckIcon")
    public static class RenderDeckIcon{
        public static void Postfix(TopPanel __instance, SpriteBatch sb) {
            if (AbstractDungeon.player instanceof Elizabeth && !AbstractDungeon.getCurrRoom().combatEvent || AbstractDungeon.getCurrRoom().monsters.monsters.size() == 0) {
                PersonaFusionButton.renderPersonaFusionButton(sb);
            }
        }
    }


    @SpirePatch(clz = TopPanel.class, method = "updateButtons")
    public static class UpdateButtons {
        public static void Postfix(TopPanel __instance) {
            if (AbstractDungeon.player instanceof Elizabeth) {
                PersonaFusionButton.updatePersonaFusionButton();
            }
        }
    }
}
