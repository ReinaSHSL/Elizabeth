package ElizabethMod.arcana.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class PriestessPower extends AbstractArcanaPower {
    private static final String POWER_ID = "Elizabeth:PriestessPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public PriestessPower() {
        this.name = NAME;
        this.ID = POWER_ID;
        updateDescription();
        this.img = getPriestessPowerTexture();
        this.canGoNegative = false;
    }

    @Override
    public void onInitialApplication() {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 5));
    }

    @Override
    public void updateDescription()
    {
        this.description = (DESCRIPTIONS[0]);
    }

    private static Texture getPriestessPowerTexture() {
        return new Texture("ElizabethImgs/powers/PriestessPower.png");
    }
}
