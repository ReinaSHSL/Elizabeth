package ElizabethMod.cards;

import ElizabethMod.enums.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class AbstractPersonaCard extends CustomCard {
    public boolean isPersona = true;
    public String Arcana;

    public AbstractPersonaCard(final String id, final String name, final String img, final int cost, final String rawDescription,
                              final AbstractCard.CardType type, final AbstractCard.CardColor color,
                              final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    public abstract String setArcana();

    public boolean isPersona() {
        return this.isPersona;
    }
}
