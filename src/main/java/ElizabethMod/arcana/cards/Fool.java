package ElizabethMod.arcana.cards;

import ElizabethMod.enums.AbstractCardEnum;
import ElizabethMod.arcana.powers.FoolPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class Fool extends AbstractArcanaCard {
    public static final String ID = "Elizabeth:Fool";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "ElizabethImgs/cards/Fool.png";
    private static final int COST = 0;
    private static final CardRarity rarity = CardRarity.SPECIAL;
    private static final CardTarget target = CardTarget.SELF;

    public Fool() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.VELVET_BLUE,
                rarity, target);
        this.arcanaString = "Fool";
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public AbstractCard makeCopy() {
        return new Fool();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }
    }
}
