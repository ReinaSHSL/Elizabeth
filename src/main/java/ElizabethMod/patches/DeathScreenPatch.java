package ElizabethMod.patches;

import ElizabethMod.character.Elizabeth;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.DeathScreen;

@SpirePatch (
        clz = DeathScreen.class,
        method="getDeathText"
)
public class DeathScreenPatch {

    @SpirePrefixPatch
    public static SpireReturn<Object> Postfix() {
        if (AbstractDungeon.player instanceof Elizabeth) {
            String deathString = "Death is not a hunter unbeknownst to its prey, one is always aware that it lies in wait.";
            return SpireReturn.Return(deathString);
        }
        return SpireReturn.Continue();
    }
}
