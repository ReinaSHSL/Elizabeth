package ElizabethMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EqualizeHPAction extends AbstractGameAction {
    private int totalHP;

    public EqualizeHPAction() {
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                this.totalHP += m.currentHealth;
            }
            int avgHP = this.totalHP / AbstractDungeon.getCurrRoom().monsters.monsters.size();
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                m.currentHealth = avgHP;
            }
            this.isDone = true;
        }
    }
}
