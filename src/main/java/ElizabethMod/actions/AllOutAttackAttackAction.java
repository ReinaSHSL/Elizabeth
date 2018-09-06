package ElizabethMod.actions;

import ElizabethMod.effects.AoaCutIn;
import ElizabethMod.effects.BlueCleaveEffect;
import ElizabethMod.effects.InvertedCleaveEffect;
import ElizabethMod.powers.DownedPower;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AllOutAttackAttackAction extends AbstractGameAction {
    private SpriteBatch sb;

    public AllOutAttackAttackAction() {
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new AoaCutIn()));
            AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new BlueCleaveEffect(), 0.0f));
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(AbstractDungeon.player,
                        m.maxHealth/16, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
            AbstractDungeon.actionManager.addToBottom(new WaitAction(2F));
            AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new InvertedCleaveEffect(), 0.0f));
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(AbstractDungeon.player,
                        m.maxHealth/16, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
            for (int i = 0; i < 2; i++) {
                for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(AbstractDungeon.player,
                            m.maxHealth/16, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                }
            }
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, AbstractDungeon.player, DownedPower.POWER_ID));
            }
            this.isDone = true;
        }
    }

}
