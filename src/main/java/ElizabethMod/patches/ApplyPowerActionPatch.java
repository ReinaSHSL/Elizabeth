package ElizabethMod.patches;

import ElizabethMod.powers.AuthorityPower;
import ElizabethMod.powers.DownedPower;
import ElizabethMod.powers.JusticeDamagePower;
import ElizabethMod.powers.StunMonsterPower;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.Collections;

@SpirePatch(
        clz= ApplyPowerAction.class,
        method="update"
)
public class ApplyPowerActionPatch {

    @SpirePrefixPatch
    public static SpireReturn<Object> AuthorityPatch(ApplyPowerAction __instance) {
        AbstractPower power = (AbstractPower) ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "powerToApply");
        if (__instance.target.hasPower(AuthorityPower.POWER_ID) && power.type == AbstractPower.PowerType.DEBUFF &&
                !power.ID.equals(StunMonsterPower.POWER_ID) && !power.ID.equals(DownedPower.POWER_ID)) {
            __instance.target.getPower(AuthorityPower.POWER_ID).flashWithoutSound();
            __instance.target.getPower(AuthorityPower.POWER_ID).onSpecificTrigger();
            __instance.isDone = true;
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }

    @SpirePrefixPatch
    public static SpireReturn<Object> JusticePatch(ApplyPowerAction __instance) {
        ArrayList<AbstractPower> justiceList = new ArrayList<>();
        boolean stackJustice = false;
        AbstractPower power = (AbstractPower) ReflectionHacks.getPrivate(__instance, ApplyPowerAction.class, "powerToApply");
        if (power.ID.equals(JusticeDamagePower.POWER_ID)) {
            for (AbstractPower po : __instance.target.powers) {
                if (po instanceof JusticeDamagePower) {
                    justiceList.add(po);
                }
            }
            for (AbstractPower po : justiceList) {
                JusticeDamagePower justiceCheck = (JusticeDamagePower) po;
                JusticeDamagePower justiceApply = (JusticeDamagePower) power;
                if (justiceCheck.justiceM == justiceApply.source) {
                    stackJustice = true;
                }
            }
            if (stackJustice) {
                for (AbstractPower po : justiceList) {
                    JusticeDamagePower justiceApply = (JusticeDamagePower) power;
                    JusticeDamagePower justiceCheck = (JusticeDamagePower)po;
                    if (justiceApply.source == justiceCheck.justiceM) {
                        po.stackPower(power.amount);
                        po.updateDescription();
                        AbstractDungeon.onModifyPower();
                        __instance.isDone = true;
                        return SpireReturn.Return(null);
                    }
                }
            } else {
                __instance.target.powers.add(power);
                Collections.sort(__instance.target.powers);
                power.onInitialApplication();
                AbstractDungeon.onModifyPower();
                power.updateDescription();
                __instance.isDone = true;
                return SpireReturn.Return(null);
            }
        }
        return SpireReturn.Continue();
    }
}

