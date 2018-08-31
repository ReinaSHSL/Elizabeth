package ElizabethMod.arcana.powers;

import ElizabethMod.arcana.cards.AbstractArcanaCard;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EmperorPower extends AbstractArcanaPower {
    private static final String POWER_ID = "Elizabeth:EmperorPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public EmperorPower() {
        this.name = NAME;
        this.ID = POWER_ID;
        updateDescription();
        this.img = getEmperorPowerTexture();
        this.canGoNegative = false;
    }

    @Override
    public void onInitialApplication() {
    }

    @Override
    public void updateDescription()
    {
        this.description = (DESCRIPTIONS[0]);
    }

    private static Texture getEmperorPowerTexture() {
        return new Texture("ElizabethImgs/powers/EmperorPower.png");
    }
}
