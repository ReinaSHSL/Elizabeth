package ElizabethMod.arcana.powers;

import ElizabethMod.cards.AbstractPersonaCard;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class EmpressPower extends AbstractArcanaPower {
    private static final String POWER_ID = "Elizabeth:EmpressPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    
    public EmpressPower(AbstractPlayer owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        updateDescription();
        this.owner = owner;
        this.img = getEmpressPowerTexture();
        this.canGoNegative = false;
    }

   @Override
   public void onInitialApplication() {
       AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
              new DexterityPower(AbstractDungeon.player, 1)));
   }

   @Override
   public void onRemove() {
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player,
                DexterityPower.POWER_ID, 1));
   }

    @Override
    public void updateDescription()
    {
        this.description = (DESCRIPTIONS[0]);
    }

    private static Texture getEmpressPowerTexture() {
        return new Texture("ElizabethImgs/powers/EmpressPower.png");
    }
}
