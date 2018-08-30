package ElizabethMod.cards.special;

import ElizabethMod.enums.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import basemod.interfaces.PostDrawSubscriber;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class WildCard extends CustomCard {
    public static final String ID = "Elizabeth:WildCard";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "cards/WildCard.png";
    private static final int COST = 0;
    private static final CardRarity rarity = CardRarity.SPECIAL;
    private static final CardTarget target = CardTarget.SELF;

    public WildCard() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.VELVET_BLUE,
                rarity, target);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToTop(new ArcanaSwapAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new WildCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }
    }


}
