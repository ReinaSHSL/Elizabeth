package ElizabethMod.cards.arcana;

import ElizabethMod.arcana.AbstractArcanaPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class AbstractArcanaCard extends CustomCard {
    public AbstractArcanaPower arcanaPower;

    public AbstractArcanaCard(final String id, final String name, final String img, final int cost, final String rawDescription,
                               final AbstractCard.CardType type, final AbstractCard.CardColor color,
                               final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    abstract AbstractArcanaPower setArcanaPower();
}
