package ElizabethMod.arcana.powers;

import ElizabethMod.arcana.powers.AbstractArcanaPower;
import ElizabethMod.cards.AbstractPersonaCard;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class FoolPower extends AbstractArcanaPower {
    public static final String POWER_ID = "Elizabeth:FoolPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public FoolPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        updateDescription();
        this.img = getFoolPowerTexture();
        this.canGoNegative = false;
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction action) {
        if (c instanceof AbstractPersonaCard && !((AbstractPersonaCard) c).doBonus) {
            ((AbstractPersonaCard) c).doBonus = true;
            this.amount -= 1;
        }
    }

    @Override
    public void updateDescription()
    {
        if (this.amount > 0) {
            this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
        } else {
            this.description = (DESCRIPTIONS[2]);
        }

    }

    private static Texture getFoolPowerTexture() {
        return new Texture("ElizabethImgs/powers/FoolPower.png");
    }
}
