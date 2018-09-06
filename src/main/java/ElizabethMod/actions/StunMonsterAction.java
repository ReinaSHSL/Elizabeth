package ElizabethMod.actions;

import ElizabethMod.powers.DownedPower;
import ElizabethMod.powers.FrozenPower;
import ElizabethMod.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import java.lang.reflect.Field;

public class StunMonsterAction extends AbstractGameAction {
    private String stunType;

    public StunMonsterAction(AbstractMonster target, AbstractCreature source, String stunType) {
        this.target = target;
        this.source = source;
        actionType = AbstractGameAction.ActionType.DEBUFF;
        duration = Settings.ACTION_DUR_FAST;
        this.stunType = stunType;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if (stunType.equals(DownedPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, source, new DownedPower((AbstractMonster) target)));
            }
            if (stunType.equals(FrozenPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, source, new FrozenPower((AbstractMonster) target, 1), 1));
            }
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, source, new StunMonsterPower((AbstractMonster) target, 1), 1));
            if (!target.hasPower(ArtifactPower.POWER_ID)) {
                try {
                    Field f = AbstractMonster.class.getDeclaredField("move");
                    f.setAccessible(true);
                    EnemyMoveInfo move = (EnemyMoveInfo) f.get(target);
                    move.intent = AbstractMonster.Intent.STUN;
                    ((AbstractMonster) target).createIntent();
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
        tickDuration();
    }
}