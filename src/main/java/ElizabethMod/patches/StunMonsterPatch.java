package ElizabethMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.relics.Cauldron;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

@SpirePatch(
        cls="com.megacrit.cardcrawl.actions.GameActionManager",
        method="getNextAction"
)
public class StunMonsterPatch {
    public static ExprEditor Instrument() {
        return new ExprEditor() {
            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                if (m.getClassName().equals("com.megacrit.cardcrawl.monsters.AbstractMonster")
                        && m.getMethodName().equals("takeTurn")) {
                    m.replace("if (!m.hasPower(ElizabethMod.powers.DownedPower.POWER_ID)) {" +
                            "$_ = $proceed($$);" +
                            "}");
                }
            }
        };
    }

}