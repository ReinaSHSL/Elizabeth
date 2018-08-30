package ElizabethMod.actions;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.arcana.AbstractArcanaPower;
import ElizabethMod.cards.arcana.AbstractArcanaCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ArcanaSwapAction extends AbstractGameAction {
    private static String[] TEXT;
    private AbstractPlayer p;

    public ArcanaSwapAction(AbstractPlayer p) {
        this.p = p;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        AbstractArcanaCard card;
        for (AbstractPower po : this.p.powers) {
            if (po instanceof AbstractArcanaPower) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, po.ID));
            }
        }
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractArcanaCard c : ElizabethModInitializer.arcanaList) {
                tmp.addToTop(c);
            }
            if (tmp.size() == 0) {
                this.isDone = true;
            } else {
                AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0], false);
                this.tickDuration();
            }
        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    card = (AbstractArcanaCard)c;
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, card.arcanaPower));
                    this.isDone = true;
                }

            }
        }
    }

    static {
        String[] uiText = {"The Arcana is the means by which all is revealed."};
        TEXT = uiText;
    }
}

