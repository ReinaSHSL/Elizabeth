package ElizabethMod.powers;

import ElizabethMod.tools.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class JusticeDamagePower extends AbstractPower {
    public static final String POWER_ID = "Elizabeth:JusticeDamagePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public AbstractCreature justiceM = null;
    public AbstractCreature source;


    public JusticeDamagePower(AbstractCreature owner, AbstractCreature m, AbstractCreature source, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.justiceM = m;
        this.source = source;
        this.amount = amount;
        updateDescription();
        this.img = getJusticeDamagePowerTexture();
        this.canGoNegative = false;
    }

    @Override
    public void atEndOfRound() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
    }

    @Override
    public void updateDescription()
    {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + justiceM.name + "."   );
    }


    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    private static Texture getJusticeDamagePowerTexture() {
        return TextureLoader.getTexture("ElizabethImgs/powers/JusticeDamagePower.png");
    }
}
