package ElizabethMod.patches;

import ElizabethMod.cards.AbstractPersonaCard;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.shop.ShopScreen;

import java.util.ArrayList;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.shop.ShopScreen",
        method = "init"
)
public class ShopScreenPatch {

    @SpirePostfixPatch
    public static void Postfix(ShopScreen __instance) {
        ArrayList<AbstractCard> reflectedList = (ArrayList<AbstractCard>)ReflectionHacks.getPrivate(__instance,
                ShopScreen.class, "coloredCards");
        for (AbstractCard c : reflectedList) {
            if (c instanceof AbstractPersonaCard) {
                reflectedList.remove(c);
            }
        }
    }
}
