package ElizabethMod.arcana.powers;

import ElizabethMod.actions.DuplicateRandomEnemyAction;
import ElizabethMod.tools.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.DrawPower;

public class DeathPower extends AbstractArcanaPower {
    private static final String POWER_ID = "Elizabeth:DeathPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public DeathPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        updateDescription();
        this.img = getDeathPowerTexture();
        this.canGoNegative = false;
    }

    @Override
    public void onInitialApplication() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new ConfusionPower(AbstractDungeon.player)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new DrawPower(AbstractDungeon.player, 1)));
        AbstractDungeon.actionManager.addToBottom(new DuplicateRandomEnemyAction());
    }

    @Override
    public void onRemove() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, DrawPower.POWER_ID));
    }

    @Override
    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToTop(new GainEnergyAction(1));
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

    private static Texture getDeathPowerTexture() {
        return TextureLoader.getTexture("ElizabethImgs/powers/DeathPower.png");
    }
}
