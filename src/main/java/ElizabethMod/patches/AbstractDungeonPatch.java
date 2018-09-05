package ElizabethMod.patches;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.enums.ElizabethEnum;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ElizabethMod.character.Elizabeth;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Cauldron;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class AbstractDungeonPatch {

        @SpirePatch(clz = AbstractDungeon.class, method = "getBossRewardCards")
        public static class BossPatch {
            public static SpireReturn<Object> Prefix() {
                ArrayList<AbstractCard> cards = new ArrayList<>();
                if (AbstractDungeon.player instanceof Elizabeth) {
                    return SpireReturn.Return(cards);
                } else {
                    return SpireReturn.Continue();
                }
            }
        }

    @SpirePatch(clz = AbstractDungeon.class, method = "render")
    public static class Render {

        @SpireInsertPatch(rloc = 111) //112
        public static void Insert(AbstractDungeon __instance, SpriteBatch sb) {
            if(AbstractDungeon.screen == ScreenStatePatch.PERSONA_FUSION_SCREEN)
                ElizabethModInitializer.personaFusionScreen.render(sb);
        }
    }

    @SpirePatch(clz = AbstractDungeon.class, method = "getRewardCards")
    public static class RewardsPatch {
        public static SpireReturn<Object> Prefix() {
            ArrayList<AbstractCard> cards = new ArrayList<>();
            if (AbstractDungeon.player instanceof Elizabeth) {
                return SpireReturn.Return(cards);
            } else {
                return SpireReturn.Continue();
            }
        }
    }

    @SpirePatch(clz=AbstractDungeon.class, method="closeCurrentScreen")
    public static class CloseCurrentScreen {
        public static void Prefix() {
            if(AbstractDungeon.screen == ScreenStatePatch.PERSONA_FUSION_SCREEN) {
                try {
                    Method overlayReset = AbstractDungeon.class.getDeclaredMethod("genericScreenOverlayReset");
                    overlayReset.setAccessible(true);
                    overlayReset.invoke(AbstractDungeon.class);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                AbstractDungeon.overlayMenu.hideBlackScreen();
                ElizabethModInitializer.personaFusionScreen.close();
            }
        }
    }

    @SpirePatch(clz = AbstractDungeon.class, method = "update")
    public static class Update {

        @SpireInsertPatch(rloc = 94)
        public static void Insert(AbstractDungeon __instance) {
            if(AbstractDungeon.screen == ScreenStatePatch.PERSONA_FUSION_SCREEN)
              ElizabethModInitializer.personaFusionScreen.update();
        }
    }
}
