package ElizabethMod.actions;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.cards.screencards.AllOutAttackNo;
import ElizabethMod.cards.screencards.AllOutAttackYes;
import ElizabethMod.character.Elizabeth;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

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
            Texture texture = new Texture(Gdx.files.internal("ElizabethImgs/char/aoaCutIn"));
            Sprite sprite = new Sprite(texture, -750F * Settings.scale, 200F * Settings.scale, 999);
        }
    }
}
