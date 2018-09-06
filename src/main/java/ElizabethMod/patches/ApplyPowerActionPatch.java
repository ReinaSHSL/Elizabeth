package ElizabethMod.patches;

import ElizabethMod.powers.AuthorityPower;
import ElizabethMod.powers.DownedPower;
import ElizabethMod.powers.StunMonsterPower;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.AbstractPower;

@SpirePatch(
        clz= ApplyPowerAction.class,
        method="update"
)
public class ApplyPowerActionPatch {

    @SpirePrefixPatch
    public static SpireReturn<Object> Prefix(ApplyPowerAction __instance) {
        AbstractPower power = (AbstractPower)ReflectionHacks.getPrivate(__instance,  ApplyPowerAction.class, "powerToApply");
        if (__instance.target.hasPower(AuthorityPower.POWER_ID) && power.type == AbstractPower.PowerType.DEBUFF &&
                !power.ID.equals(StunMonsterPower.POWER_ID) && !power.ID.equals(DownedPower.POWER_ID)) {
            System.out.println("IT PROCC " + power.ID);
            __instance.target.getPower(AuthorityPower.POWER_ID).flashWithoutSound();
            __instance.target.getPower(AuthorityPower.POWER_ID).onSpecificTrigger();
            __instance.isDone = true;
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }
}
