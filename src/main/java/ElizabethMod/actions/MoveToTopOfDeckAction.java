package ElizabethMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MoveToTopOfDeckAction extends AbstractGameAction {

    public MoveToTopOfDeckAction() {
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.drawPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            CardGroup tmpGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                tmpGroup.addToTop(c);
            }
            AbstractDungeon.gridSelectScreen.open(tmpGroup, 1, false, "Sleight of Hand");
            tickDuration();
        } else {
            for (final AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                AbstractDungeon.player.drawPile.moveToDeck(c, false);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
        }
    }
}
