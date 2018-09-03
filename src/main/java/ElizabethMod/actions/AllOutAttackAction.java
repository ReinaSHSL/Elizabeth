package ElizabethMod.actions;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.cards.screencards.AllOutAttackNo;
import ElizabethMod.cards.screencards.AllOutAttackYes;
import ElizabethMod.character.Elizabeth;
import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoiceBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.AllOutAttack;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Sprite;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class AllOutAttackAction extends AbstractGameAction implements ModalChoice.Callback{
    private ArrayList<AbstractCard> list = new ArrayList<>();
    private boolean allOutAttackChoice;
    private SpriteBatch sb;
    private ModalChoice modal;

    public AllOutAttackAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        modal = new ModalChoiceBuilder()
                .setCallback(this)
                .addOption(new AllOutAttackYes ())
                .addOption(new AllOutAttackNo())
                .create();
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            modal.open();
            tickDuration();
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

    @Override
    public void optionSelected(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster, int i) {
        switch(i){
            case 0:
                allOutAttack();
            case 1:
                break;
            default:
                System.out.println("UPDATE BODY TEXT");
                break;
        }
    }
}
