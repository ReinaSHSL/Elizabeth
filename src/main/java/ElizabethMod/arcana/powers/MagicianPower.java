package ElizabethMod.arcana.powers;

import ElizabethMod.arcana.cards.AbstractArcanaCard;
import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.patches.AbstractDungeonPatch;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class MagicianPower extends AbstractArcanaPower {
    private static final String POWER_ID = "Elizabeth:MagicianPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public MagicianPower() {
        this.name = NAME;
        this.ID = POWER_ID;
        updateDescription();
        this.img = getMagicianPowerTexture();
        this.canGoNegative = false;
    }

    @Override
    public void onInitialApplication() {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 2));
    }

    @Override
    public void updateDescription()
    {
            this.description = (DESCRIPTIONS[0]);
    }

    private static Texture getMagicianPowerTexture() {
        return new Texture("ElizabethImgs/powers/MagicianPower.png");
    }
}
