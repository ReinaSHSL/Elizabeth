package ElizabethMod.arcana;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractArcanaPower extends AbstractPower {
    public final boolean isArcana = true;

    public AbstractArcanaPower() {
    }

    public boolean arcanaCheck() {
        return this.isArcana;
    }

    public static String getPlayerArcana() {
        for (AbstractPower po : AbstractDungeon.player.powers) {
            if (po instanceof AbstractArcanaPower) {
                return po.name;
            }
        }
        return null;
    }
}
