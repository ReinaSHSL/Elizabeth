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
    private ModalChoice modal;

    public AllOutAttackAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        modal = new ModalChoiceBuilder()
                .setCallback(this)
                .setTitle("It's our chance for an All Out Attack!")
                .addOption(new AllOutAttackYes ())
                .addOption(new AllOutAttackNo())
                .create();
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            modal.open();
            this.isDone = true;
        }
    }

    @Override
    public void optionSelected(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster, int i) {
        this.isDone = true;
    }
}
