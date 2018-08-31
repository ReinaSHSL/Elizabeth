package ElizabethMod.patches;

import ElizabethMod.powers.AuthorityPower;
import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

@SpirePatch(
        cls= "com.megacrit.cardcrawl.actions.common.ApplyPowerAction",
        method="update"
)
public class ApplyPowerActionPatch {

    @SpirePrefixPatch
    public static void Prefix(ApplyPowerAction __instance) {
        AbstractPower power = (AbstractPower)ReflectionHacks.getPrivate(__instance,  ApplyPowerAction.class, "powerToApply");
        if (__instance.target.hasPower(AuthorityPower.POWER_ID) && power.type == AbstractPower.PowerType.DEBUFF) {
            __instance.target.getPower(AuthorityPower.POWER_ID).flashWithoutSound();
            __instance.target.getPower(AuthorityPower.POWER_ID).onSpecificTrigger();
        }
    }
}
