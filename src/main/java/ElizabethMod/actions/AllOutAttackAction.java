package ElizabethMod.actions;

import ElizabethMod.cards.screencards.AllOutAttackNo;
import ElizabethMod.cards.screencards.AllOutAttackYes;
import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoiceBuilder;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AllOutAttackAction extends AbstractGameAction implements ModalChoice.Callback{
    private ModalChoice modal;

    public AllOutAttackAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        modal = new ModalChoiceBuilder()
                .setCallback(this)
                .setTitle("All Out Attack!")
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
