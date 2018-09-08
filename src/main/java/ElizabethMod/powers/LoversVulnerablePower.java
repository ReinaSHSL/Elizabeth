package ElizabethMod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class LoversVulnerablePower extends AbstractPower {
    public static final String POWER_ID = "Elizabeth:LoversVulnerablePower";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private boolean justApplied;
    private static final float EFFECTIVENESS = 1.5f;
    private static final int EFFECTIVENESS_STRING = 50;

    public LoversVulnerablePower(final AbstractCreature owner, final int amount, final boolean isSourceMonster) {
        this.justApplied = false;
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("vulnerable");
        if (AbstractDungeon.actionManager.turnHasEnded && isSourceMonster) {
            this.justApplied = true;
        }
        this.type = PowerType.DEBUFF;
    }

    @Override
    public void atEndOfRound() {
        if (this.justApplied) {
            this.justApplied = false;
            return;
        }
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, ID));
        }
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            if (this.owner != null && this.owner.isPlayer && AbstractDungeon.player.hasRelic("Odd Mushroom")) {
                this.description = VulnerablePower.DESCRIPTIONS[0] + 25 + VulnerablePower.DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[0];
            }
            else if (this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Frog")) {
                this.description = VulnerablePower.DESCRIPTIONS[0] + 75 + VulnerablePower.DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[0];
            }
            else {
                this.description = VulnerablePower.DESCRIPTIONS[0] + 50 + VulnerablePower.DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[0];
            }
        }
        else if (this.owner != null && this.owner.isPlayer && AbstractDungeon.player.hasRelic("Odd Mushroom")) {
            this.description = VulnerablePower.DESCRIPTIONS[0] + 25 + VulnerablePower.DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[0];
        }
        else if (this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Frog")) {
            this.description = VulnerablePower.DESCRIPTIONS[0] + 75 + VulnerablePower.DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[0];
        }
        else {
            this.description = VulnerablePower.DESCRIPTIONS[0] + 50 + VulnerablePower.DESCRIPTIONS[1] + DESCRIPTIONS[0];
        }
    }

    @Override
    public float atDamageReceive(final float damage, final DamageInfo.DamageType type) {
        if (type != DamageInfo.DamageType.NORMAL) {
            return damage;
        }
        if (this.owner.isPlayer && AbstractDungeon.player.hasRelic("Odd Mushroom")) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, ID));
            return damage * 1.25f;
        }
        if (this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Frog")) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, ID));
            return damage * 1.75f;
        }
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, ID));
        return damage * 1.5f;
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
