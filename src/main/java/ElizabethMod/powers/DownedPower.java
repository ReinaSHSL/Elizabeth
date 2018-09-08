package ElizabethMod.powers;

import ElizabethMod.actions.AllOutAttackAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DownedPower extends AbstractPower {
    public static final String POWER_ID = "Elizabeth:DownedPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DownedPower(AbstractMonster owner) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        amount = 1;
        type = PowerType.DEBUFF;
        updateDescription();
        img = ImageMaster.loadImage("ElizabethImgs/powers/DownedPower.png");
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void onRemove() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, AbstractDungeon.player, StunMonsterPower.POWER_ID));
    }

    @Override
    public void atEndOfRound() {
        reducePower(1);
        if (amount <= 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
        }
    }

    @Override
    public void onInitialApplication() {
        int downedCount = 0;
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower(DownedPower.POWER_ID)) {
                downedCount++;
            }
        }
        if (downedCount == AbstractDungeon.getCurrRoom().monsters.monsters.size()) {
            AbstractDungeon.actionManager.addToTop(new AllOutAttackAction());
        }
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type != DamageInfo.DamageType.NORMAL) {
            return damage;
        }
        return damage * 1.25F;
    }
}
