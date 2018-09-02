package ElizabethMod.patches;

import ElizabethMod.enums.ElizabethEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ElizabethMod.character.Elizabeth;

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
}
