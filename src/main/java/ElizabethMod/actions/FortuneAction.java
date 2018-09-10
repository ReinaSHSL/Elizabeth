package ElizabethMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class FortuneAction extends AbstractGameAction {

    public static final String[] TEXT = {"May fortune smile upon thee."};

    public FortuneAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.drawPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            final CardGroup tmpGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                tmpGroup.addToTop(c);
            }
            AbstractDungeon.gridSelectScreen.open(tmpGroup, 3, true, FortuneAction.TEXT[0]);
        }
        else if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (final AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                AbstractDungeon.player.drawPile.moveToDiscardPile(c);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            int rng = AbstractDungeon.cardRng.random(AbstractDungeon.player.drawPile.size() - 1);
            AbstractCard c = AbstractDungeon.player.drawPile.getNCardFromTop(rng);
            AbstractDungeon.player.drawPile.moveToHand(c, AbstractDungeon.player.hand);
        }
        this.tickDuration();
        this.isDone = true;
    }

}
