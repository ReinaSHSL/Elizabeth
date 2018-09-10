package ElizabethMod.powers;

import ElizabethMod.cards.AbstractPersonaCard;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class IcarusPower extends AbstractPower {
    public static final String POWER_ID = "Elizabeth:IcarusPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public IcarusPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        updateDescription();
        this.img = getIcarusPowerTexture();
        this.canGoNegative = false;
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction a) {
        this.flash();
        a.exhaustCard = true;
    }


    @Override
    public void updateDescription()
    {
        this.description = (DESCRIPTIONS[0]);
    }

    private static Texture getIcarusPowerTexture() {
        return new Texture("ElizabethImgs/powers/IcarusPower.png");
    }
}
