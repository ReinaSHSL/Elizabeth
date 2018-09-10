package ElizabethMod.powers;

import ElizabethMod.patches.AbstractCardPatch;
import ElizabethMod.tools.TextureLoader;
import basemod.BaseMod;
import basemod.interfaces.PostBattleSubscriber;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class ModerationPower extends AbstractPower implements PostBattleSubscriber {
    private static final String POWER_ID = "Elizabeth:ModerationPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int cardsPlayed;


    public ModerationPower(AbstractCreature owner) {
        BaseMod.subscribe(this);
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        updateDescription();
        this.img = getModerationPowerTexture();
        this.canGoNegative = false;
    }

    @Override
    public void onInitialApplication() {
        this.cardsPlayed = AbstractDungeon.player.cardsPlayedThisTurn;
        if (AbstractDungeon.player.cardsPlayedThisTurn >= 2) {
            AbstractCardPatch.canPlayField.canPlay.set(AbstractDungeon.player, false);
        }
        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public void atEndOfRound() {
        AbstractCardPatch.canPlayField.canPlay.set(AbstractDungeon.player, true);
        reducePower(1);
        if (amount <= 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
        }
    }


    @Override
    public void onUseCard(AbstractCard c, UseCardAction a) {
        this.cardsPlayed = AbstractDungeon.player.cardsPlayedThisTurn;
        if (AbstractDungeon.player.cardsPlayedThisTurn >= 2) {
            AbstractCardPatch.canPlayField.canPlay.set(AbstractDungeon.player, false);
        }
        updateDescription();
    }

    private float calculateDamageTakenAmount(float damage, DamageInfo.DamageType type)
    {
        if (type != DamageInfo.DamageType.HP_LOSS && type != DamageInfo.DamageType.THORNS) {
            return damage / 2.0F;
        }
        return damage;
    }

    @Override
    public float atDamageReceive(float damageAmount, DamageInfo.DamageType type)
    {
        boolean willLive = calculateDamageTakenAmount(damageAmount, type) < owner.currentHealth;
        if (type != DamageInfo.DamageType.HP_LOSS
                && type != DamageInfo.DamageType.THORNS
                && damageAmount > 0
                && willLive) {
            flash();
            damageAmount = Math.round(calculateDamageTakenAmount(damageAmount, type));
        }
        return damageAmount;
    }


    @Override
    public void updateDescription() {
        if (this.cardsPlayed == 1) {
            this.description = (DESCRIPTIONS[0] + this.cardsPlayed + DESCRIPTIONS[1]);
        } else {
            this.description = (DESCRIPTIONS[0] + this.cardsPlayed + DESCRIPTIONS[2]);
        }

    }

    private static Texture getModerationPowerTexture() {
        return TextureLoader.getTexture("ElizabethImgs/powers/ModerationPower.png");
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        AbstractCardPatch.canPlayField.canPlay.set(AbstractDungeon.player, true);
    }
}
