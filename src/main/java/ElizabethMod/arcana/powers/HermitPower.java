package ElizabethMod.arcana.powers;

import ElizabethMod.tools.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.DiscardPileToHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class HermitPower extends AbstractArcanaPower {
    private static final String POWER_ID = "Elizabeth:HermitPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public HermitPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        updateDescription();
        this.img = getHermitPowerTexture();
        this.canGoNegative = false;
    }

    @Override
    public void onInitialApplication() {
        if (AbstractDungeon.player.discardPile.size() > 0) {
            AbstractDungeon.actionManager.addToBottom(new DiscardPileToHandAction(1));
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(-2));
        }
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

    private static Texture getHermitPowerTexture() {
        return TextureLoader.getTexture("ElizabethImgs/powers/HermitPower.png");
    }
}
