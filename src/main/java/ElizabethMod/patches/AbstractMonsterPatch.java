package ElizabethMod.patches;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.character.Elizabeth;
import ElizabethMod.enums.ElizabethEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;

public class AbstractMonsterPatch {

    @SpirePatch(clz = AbstractMonster.class, method = "die", paramtypez = {boolean.class})
    public static class GainPersonaUponDeathPatch {
        public static void Postfix(AbstractMonster __instance, boolean triggerRelics)
        {
            if (AbstractDungeon.player instanceof Elizabeth) {
                int rngSeed = AbstractDungeon.cardRandomRng.random(30);
                AbstractCard c = ElizabethModInitializer.listOfBasicPersona.get(rngSeed);
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction
                        (c, 1, true, false));
                AbstractDungeon.effectsQueue.add(new FastCardObtainEffect(c, c.current_x, c.current_y));
            }
        }
    }

}
