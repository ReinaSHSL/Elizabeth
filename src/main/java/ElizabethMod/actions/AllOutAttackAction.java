package ElizabethMod.actions;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.cards.screencards.AllOutAttackNo;
import ElizabethMod.cards.screencards.AllOutAttackYes;
import ElizabethMod.character.Elizabeth;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.AllOutAttack;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Sprite;

import java.util.ArrayList;

public class AllOutAttackAction extends AbstractGameAction {
    private ArrayList<AbstractCard> list = new ArrayList<>();
    private boolean allOutAttackChoice;
    private SpriteBatch sb;

    public AllOutAttackAction() {
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            this.list.add(new AllOutAttackYes());
            this.list.add(new AllOutAttackNo());
            ElizabethModInitializer.aoas.open(this.list, null, "Here's our chance for an All Out Attack!");
            tickDuration();
            return;
        }
        if (ElizabethModInitializer.aoas.allOutAttackChoice.cardID.equals(AllOutAttackNo.ID)) {
            return;
        }
        if (ElizabethModInitializer.aoas.allOutAttackChoice.cardID.equals(AllOutAttackYes.ID)) {
            allOutAttack();
        }
    }

    private void allOutAttack() {
        float y = Settings.HEIGHT - 300F * Settings.scale;
        float x = Settings.WIDTH / 2F;
        Texture cutIn = new Texture("ElizabethImgs/powers/DownedPower.png");
        sb.draw(cutIn, x, y,
                556.0f, 119.0f, 1112.0f, 238.0f, Settings.scale, Settings.scale,
                0.0f, 0, 0, 1112, 238, false, false);
    }
}
