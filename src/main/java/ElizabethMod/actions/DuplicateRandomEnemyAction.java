package ElizabethMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.GremlinNob;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class DuplicateRandomEnemyAction extends AbstractGameAction {
    
    public DuplicateRandomEnemyAction() {
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            int rng = AbstractDungeon.monsterRng.random(AbstractDungeon.getCurrRoom().monsters.monsters.size() - 1);
            float posX = 0;
            AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.monsters.get(rng);
            float posY = m.drawY;
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (mo.drawX != 0) {
                    posX = mo.drawX;
                }
            }
            AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(new m(posX - 100F, posY)), false);
        }
    }

}
