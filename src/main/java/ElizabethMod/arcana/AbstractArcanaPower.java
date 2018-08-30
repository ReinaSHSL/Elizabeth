package ElizabethMod.arcana;

import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractArcanaPower extends AbstractPower {
    public final boolean isArcana = true;

    public AbstractArcanaPower() {
    }

    public boolean arcanaCheck() {
        return this.isArcana;
    }
}
