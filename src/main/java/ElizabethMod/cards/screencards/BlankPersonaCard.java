package ElizabethMod.cards.screencards;

import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class BlankPersonaCard extends AbstractPersonaCard {
    public static final String ID = "Elizabeth:BlankPersonaCard";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "ElizabethImgs/cards/BlankPersonaCard.png";
    private static final int COST = 0;
    private static final CardRarity rarity = CardRarity.COMMON;
    private static final CardTarget target = CardTarget.SELF;

    public BlankPersonaCard() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.VELVET_BLUE,
                        rarity, target);
        this.arcana = "Universe";
        this.personaValue = 0;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.bonus();
        if (this.doBonus) {
        }
    }

    @Override
    public AbstractCard makeCopy () {
        return new BlankPersonaCard();
    }

    @Override
    public void upgrade () {
        if (!this.upgraded) {
            this.upgradeName();
        }
    }
}
