package ElizabethMod.patches;

import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.tools.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.ui.panels.TopPanel;

public class AbstractCardPatch {

    @SpirePatch(clz = AbstractCard.class, method=SpirePatch.CLASS)
    public static class Field {
        public static SpireField<Boolean> isCompendium = new SpireField<>(() -> false);
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderCard")
    public static class RenderPatch {
        private static Texture goldIcon = TextureLoader.getTexture("ElizabethImgs/ui/goldIcon.png");
        public static void Postfix(AbstractCard card, SpriteBatch sb, boolean b1, boolean b2){
            if(Field.isCompendium.get(card)){
                AbstractPersonaCard c = (AbstractPersonaCard) card;
                float currentX = card.current_x + 370.0f * card.drawScale / 3.0f;
                float currentY = card.current_y + 520.0f * card.drawScale / 3.0f;
                sb.draw(goldIcon, currentX  * Settings.scale, currentY * Settings.scale, goldIcon.getWidth(), goldIcon.getHeight());
                FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(c.goldValue),
                        (currentX - 10F)  * Settings.scale, (currentY + 40F)  * Settings.scale, Settings.GOLD_COLOR);
            }
        }
    }
}
