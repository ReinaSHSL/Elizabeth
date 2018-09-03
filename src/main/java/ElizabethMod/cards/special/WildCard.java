package ElizabethMod.cards.special;

import ElizabethMod.actions.ArcanaSwapAction;
import ElizabethMod.enums.AbstractCardEnum;
import ElizabethMod.powers.WildCardPower;
import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.DiscardPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class WildCard extends CustomCard {
    public static final String ID = "Elizabeth:WildCard";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "ElizabethImgs/cards/WildCard.png";
    private static final int COST = 0;
    private static final CardRarity rarity = CardRarity.SPECIAL;
    private static final CardTarget target = CardTarget.SELF;

    public WildCard() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.VELVET_BLUE,
                rarity, target);
        this.retain = true;
    }


    @Override
    public void applyPowers(){
        super.applyPowers();
        this.retain = true;
    }

    @Override
    public boolean hasEnoughEnergy() {
        if (AbstractDungeon.player.hasPower(WildCardPower.POWER_ID)) {
            return false;
        }
        return super.hasEnoughEnergy();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ArcanaSwapAction(p));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WildCardPower(p, 0)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new WildCard();
    }

    @Override
    public void triggerOnExhaust() {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.makeCopy()));
    }

    @Override
    public void triggerOnManualDiscard() {
        AbstractDungeon.player.discardPile.removeCard(this);
        AbstractDungeon.player.hand.addToHand(this);
    }

    @Override
    public void upgrade() {}

}
