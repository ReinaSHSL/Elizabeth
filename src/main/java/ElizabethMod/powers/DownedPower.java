package ElizabethMod.powers;

import ElizabethMod.actions.AllOutAttackAction;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DownedPower extends AbstractPower {
    public static final String POWER_ID = "Elizabeth:Downed";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private byte moveByte;
    private AbstractMonster.Intent moveIntent;

    public DownedPower(AbstractMonster owner) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        amount = 1;
        type = PowerType.DEBUFF;
        updateDescription();
        img = ImageMaster.loadImage("ElizabethImgs/powers/DownedPower.png");

        moveByte = owner.nextMove;
        moveIntent = owner.intent;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount;
        if (amount == 1) {
            description += DESCRIPTIONS[1];
        } else {
            description += DESCRIPTIONS[2];
        }
    }

    @Override
    public void atEndOfRound() {
        reducePower(1);
        if (amount <= 0) {
            if (owner instanceof AbstractMonster) {
                AbstractMonster m = (AbstractMonster) owner;
                m.setMove(moveByte, moveIntent);
                m.createIntent();
            }
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
        }
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, AbstractDungeon.player, this.ID));
        }
        return damageAmount;
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
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage * 1.25F;
        }
        return damage;
    }
}
