package ElizabethMod.patches;

import ElizabethMod.character.Elizabeth;
import ElizabethMod.powers.JusticeDamagePower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

@SpirePatch(clz = AbstractPlayer.class, method = "damage")
public class AbstractPlayerPatch {

    @SpirePrefixPatch
    public static void Prefix(AbstractPlayer __instance, DamageInfo info) {
        if (AbstractDungeon.player instanceof Elizabeth) {
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && info.type == DamageInfo.DamageType.NORMAL) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                        new JusticeDamagePower(AbstractDungeon.player, info.owner, info.owner, info.output), info.output));
            }
        }
    }
}
