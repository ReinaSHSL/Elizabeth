package ElizabethMod.arcana.cards;

import ElizabethMod.actions.EqualizeHPAction;
import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.enums.AbstractCardEnum;
import ElizabethMod.enums.ArcanaEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class Judgement extends AbstractArcanaCard {
    public static final String ID = "Elizabeth:Judgement";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "ElizabethImgs/cards/Judgement.png";
    private static final int COST = 20;
    private static final CardRarity rarity = CardRarity.SPECIAL;
    private static final CardTarget target = CardTarget.SELF;

    public Judgement() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.VELVET_BLUE,
                rarity, target);
        this.arcanaString = ArcanaEnum.Arcana.JUDGEMENT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public AbstractCard makeCopy() {
        return new Judgement();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }
    }
}
