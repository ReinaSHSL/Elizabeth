package ElizabethMod.cards.uncommonpersona;

import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class QueenMab extends AbstractPersonaCard {
    public static final String ID = "Elizabeth:QueenMab";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "ElizabethImgs/cards/QueenMab.png";
    private static final int COST = 2;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.ENEMY;

    public QueenMab() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.VELVET_BLUE,
                        rarity, target);
        this.arcana = "Lovers";
        this.personaValue = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        System.out.println("Todo");
        super.bonus();
        if (this.doBonus) {
            System.out.println("A");
        }
    }

        @Override
        public AbstractCard makeCopy () {
            return new QueenMab();
        }

        @Override
        public void upgrade () {
            if (!this.upgraded) {
                this.upgradeName();
            }
        }
    }
