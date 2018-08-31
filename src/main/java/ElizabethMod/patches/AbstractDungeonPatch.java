package ElizabethMod.patches;

import ElizabethMod.enums.ElizabethEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AbstractDungeonPatch {

    @SpirePatch(cls = "com.megacrit.cardcrawl.dungeons", method = "getBossRewardCards")
    public static class BossPatch {
        public static SpireReturn<Object> Prefix(AbstractDungeon __instance)
        {
            if (AbstractDungeon.player.chosenClass == ElizabethEnum.ATTENDANT) {
                return SpireReturn.Return(null);
            } else {
                return SpireReturn.Continue();
            }
        }
    }

    @SpirePatch(cls = "com.megacrit.cardcrawl.dungeons", method = "getRewardCards")
    public static class RewardsPatch {
        public static SpireReturn<Object> Prefix(AbstractDungeon __instance)
        {
            if (AbstractDungeon.player.chosenClass == ElizabethEnum.ATTENDANT) {
                return SpireReturn.Return(null);
            } else {
                return SpireReturn.Continue();
            }
        }
    }


}
