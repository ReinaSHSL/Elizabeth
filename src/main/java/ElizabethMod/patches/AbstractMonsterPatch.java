package ElizabethMod.patches;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.character.Elizabeth;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class AbstractMonsterPatch {

    @SpirePatch(clz = AbstractMonster.class, method = "die", paramtypez = {boolean.class})
    public static class GainPersonaUponDeathPatch {
        public static void Postfix(AbstractMonster __instance, boolean triggerRelics)
        {
            if (AbstractDungeon.player instanceof Elizabeth) {
                if (!__instance.hasPower(MinionPower.POWER_ID)) {
                    int rngSeed = AbstractDungeon.cardRandomRng.random(1);
                    AbstractCard c = ElizabethModInitializer.listOfBasicPersona.get(rngSeed);
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction
                            (c, 1, true, false));
                    AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(c, Settings.WIDTH / 2F * Settings.scale,Settings.HEIGHT / 2F * Settings.scale ));
                    ElizabethModInitializer.compendium.add((AbstractPersonaCard) c);
                }
            }
        }
    }

}
