package ElizabethMod.relics;

import ElizabethMod.cards.special.WildCard;
import ElizabethMod.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class MemoriesOfYou extends CustomRelic {
    public static final String ID = "Elizabeth:MemoriesOfYou";
    private static final Texture IMG = TextureLoader.getTexture("ElizabethImgs/relics/MemoriesOfYou.png");

    public MemoriesOfYou() {
        super(ID, IMG, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction action) {
        if (c.cardID.equals(WildCard.ID)) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1, false));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MemoriesOfYou();
    }
}
