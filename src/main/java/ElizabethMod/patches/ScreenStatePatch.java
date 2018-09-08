package ElizabethMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ScreenStatePatch {
    @SpireEnum
    public static AbstractDungeon.CurrentScreen PERSONA_FUSION_SCREEN;
    @SpireEnum
    public static AbstractDungeon.CurrentScreen COMPENDIUM_SCREEN;
}
