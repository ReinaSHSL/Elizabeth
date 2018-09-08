package ElizabethMod.patches;

import ElizabethMod.character.Elizabeth;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;

@SpirePatch(clz = CombatRewardScreen.class, method = "setupItemReward" )
public class CombatRewardScreenPatch {

    @SpirePrefixPatch
    public static SpireReturn<Object> Prefix() {
        if (AbstractDungeon.player instanceof Elizabeth) {
            AbstractDungeon.overlayMenu.proceedButton.show();
            return SpireReturn.Return(null);
        } else {
            return SpireReturn.Continue();
        }
    }
}
