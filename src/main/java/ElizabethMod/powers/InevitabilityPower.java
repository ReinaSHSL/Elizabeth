package ElizabethMod.powers;

import ElizabethMod.tools.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static java.awt.SystemColor.info;

public class InevitabilityPower extends AbstractPower {
    public static final String POWER_ID = "Elizabeth:InevitabilityPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justApplied;


    public InevitabilityPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = getInevitabilityPowerTexture();
        this.canGoNegative = false;
        this.justApplied = true;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!this.justApplied){
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.owner,
                    new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS),
                    AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, ID));
        }
        if (this.justApplied) {
            this.justApplied = false;
            updateDescription();
        }
    }

    @Override
    public int onAttacked(DamageInfo info, int damage) {
        if (this.justApplied) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new InevitabilityPower(this.owner, damage), damage));
            return 0;
        } else {
            return damage;
        }
    }

    @Override
    public void updateDescription()
    {
        if (this.justApplied) {
            this.description = (DESCRIPTIONS[0] + this.amount * 2 + DESCRIPTIONS[1] + DESCRIPTIONS[2]);
        } else {
            this.description = (DESCRIPTIONS[1] + this.amount * 2 + DESCRIPTIONS[3]);
        }

    }


    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    private static Texture getInevitabilityPowerTexture() {
        return TextureLoader.getTexture("ElizabethImgs/powers/InevitabilityPower.png");
    }
}
