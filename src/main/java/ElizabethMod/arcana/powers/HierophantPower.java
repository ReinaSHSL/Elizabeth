package ElizabethMod.arcana.powers;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.character.Elizabeth;
import ElizabethMod.tools.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;

public class HierophantPower extends AbstractArcanaPower {
    private static final String POWER_ID = "Elizabeth:HierophantPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public HierophantPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        updateDescription();
        this.img = getHierophantPowerTexture();
        this.canGoNegative = false;
    }

    @Override
    public void onInitialApplication() {
        ArrayList<AbstractPersonaCard> hierophantCards = new ArrayList<>();
        for (AbstractPersonaCard c : ElizabethModInitializer.compendium) {
            if (c.rarity == AbstractCard.CardRarity.BASIC || c.rarity == AbstractCard.CardRarity.COMMON) {
                hierophantCards.add(c);
            }
        }
        int roll = AbstractDungeon.cardRng.random(hierophantCards.size() - 1);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(hierophantCards.get(roll),
                1, false));
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

    private static Texture getHierophantPowerTexture() {
        return TextureLoader.getTexture("ElizabethImgs/powers/HierophantPower.png");
    }
}
