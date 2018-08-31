package ElizabethMod.arcana.cards;

import ElizabethMod.arcana.powers.MagicianPower;
import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.enums.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import basemod.interfaces.PostDrawSubscriber;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class Magician extends AbstractArcanaCard {
    public static final String ID = "Elizabeth:Magician";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "ElizabethImgs/cards/Magician.png";
    private static final int COST = 0;
    private static final CardRarity rarity = CardRarity.SPECIAL;
    private static final CardTarget target = CardTarget.SELF;

    public Magician() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.VELVET_BLUE,
                rarity, target);
        this.arcanaPower = new MagicianPower();
        this.arcanaString = "Magician";
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public AbstractCard makeCopy() {
        return new Magician();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }
    }


}
