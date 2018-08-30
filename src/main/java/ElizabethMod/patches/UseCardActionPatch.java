package ElizabethMod.patches;

import ElizabethMod.cards.special.WildCard;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.actions.utility.UseCardAction",
        method = "update"
)
public class UseCardActionPatch {

    @SpireInsertPatch(
            locator = Locator.class,
            localvars = {"targetCard"}
    )
    public static SpireReturn<Object> Insert(UseCardAction __instance, AbstractCard targetCard) {
        if (targetCard.cardID.equals(WildCard.ID)) {
            AbstractDungeon.player.limbo.moveToHand(targetCard, AbstractDungeon.player.limbo);
            __instance.isDone = true;
            AbstractDungeon.player.cardInUse = null;
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(
                    "com.megacrit.cardcrawl.cards.CardGroup", "moveToDiscardPile");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
        }
    }
}
