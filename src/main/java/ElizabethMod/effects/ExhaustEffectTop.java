package ElizabethMod.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.ExhaustBlurEffect;
import com.megacrit.cardcrawl.vfx.ExhaustEmberEffect;

public class ExhaustEffectTop extends AbstractGameEffect {
    private AbstractCard c;
    private static final float DUR = 1.0f;

    public ExhaustEffectTop(final AbstractCard c) {
        this.duration = 1.0f;
        this.c = c;
    }

    @Override
    public void update() {
        if (this.duration == 1.0f) {
            CardCrawlGame.sound.play("CARD_EXHAUST", 0.2f);
            for (int i = 0; i < 90; ++i) {
                AbstractDungeon.topLevelEffectsQueue.add(new ExhaustBlurEffect(this.c.current_x, this.c.current_y));
            }
            for (int i = 0; i < 50; ++i) {
                AbstractDungeon.topLevelEffectsQueue.add(new ExhaustEmberEffect(this.c.current_x, this.c.current_y));
            }
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if (!this.c.fadingOut && this.duration < 0.7f && !AbstractDungeon.player.hand.contains(this.c)) {
            this.c.fadingOut = true;
        }
        if (this.duration < 0.0f) {
            this.isDone = true;
            this.c.resetAttributes();
        }
    }

    @Override
    public void render(final SpriteBatch sb) {
        this.c.render(sb);
    }


}
