package ElizabethMod.cards;

import ElizabethMod.arcana.powers.AbstractArcanaPower;
import ElizabethMod.enums.ArcanaEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class AbstractPersonaCard extends CustomCard {
    public boolean isPersona = true;
    public ArcanaEnum.Arcana arcana;
    public int personaValue = 1;
    public boolean doBonus = false;
    public int goldValue;

    public AbstractPersonaCard(final String id, final String name, final String img, final int cost, final String rawDescription,
                              final AbstractCard.CardType type, final AbstractCard.CardColor color,
                              final AbstractCard.CardRarity rarity, final AbstractCard.CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    public void bonus() {
        String playerArcana = AbstractArcanaPower.getPlayerArcana();
        if (playerArcana == null) {
            return;
        }
        if (playerArcana.equals(this.arcana)) {
            this.doBonus = true;
        }
    }
}
