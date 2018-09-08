package ElizabethMod.actions;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.arcana.cards.AbstractArcanaCard;
import ElizabethMod.arcana.cards.Chariot;
import ElizabethMod.arcana.powers.*;
import ElizabethMod.powers.JusticeDamagePower;
import ElizabethMod.powers.LoversVulnerablePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class ArcanaSwapAction extends AbstractGameAction {
    private static String[] TEXT;
    private AbstractPlayer p;
    private AbstractMonster m = null;
    private ArrayList<JusticeDamagePower> justiceList = new ArrayList<>();

    public ArcanaSwapAction(AbstractPlayer p) {
        this.p = p;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = 1;
    }

    @Override
    public void update() {
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        AbstractArcanaCard card;
        for (AbstractPower po : this.p.powers) {
            if (po instanceof AbstractArcanaPower) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, po.ID));
            }
        }
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractArcanaCard c : ElizabethModInitializer.arcanaList) {
                tmp.addToTop(c);
            }
            if (tmp.size() == 0) {
                this.isDone = true;
            } else {
                AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0], false);
                this.tickDuration();
            }
        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    switch (((AbstractArcanaCard) c).arcanaString) {
                        case "Fool":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new FoolPower(AbstractDungeon.player)));
                            this.isDone = true;
                            break;
                        case "Magician":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new MagicianPower(AbstractDungeon.player)));
                            this.isDone = true;
                            break;
                        case "Priestess":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new PriestessPower(AbstractDungeon.player)));
                            this.isDone = true;
                            break;
                        case "Empress":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new EmpressPower(AbstractDungeon.player)));
                            this.isDone = true;
                            break;
                        case "Emperor":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new EmperorPower(AbstractDungeon.player)));
                            new TargetAction("Emperor", 1);
                            this.isDone = true;
                            break;
                        case "Hierophant":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new HierophantPower(AbstractDungeon.player)));
                            this.isDone = true;
                            break;
                        case "Lovers":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new LoversPower(AbstractDungeon.player)));
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new LoversVulnerablePower(AbstractDungeon.player, 1, false)));
                            new TargetAction("Lovers", 1);
                            this.isDone = true;
                            break;
                        case "Chariot":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new ChariotPower(AbstractDungeon.player)));
                            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                                AbstractDungeon.actionManager.addToBottom(new DamageAction(mo,
                                        new DamageInfo(p, 3, DamageInfo.DamageType.NORMAL),
                                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                            }
                            this.isDone = true;
                            break;
                        case "Justice":
                            for (AbstractPower po : AbstractDungeon.player.powers) {
                                if (po.ID == JusticeDamagePower.POWER_ID) {
                                    justiceList.add((JusticeDamagePower) po);
                                }
                            }
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new JusticePower(AbstractDungeon.player)));
                            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                                for (JusticeDamagePower po : justiceList) {
                                    if (po.justiceM == mo) {
                                        AbstractDungeon.actionManager.addToBottom(new DamageAction(mo,
                                                new DamageInfo(p, po.amount, DamageInfo.DamageType.THORNS),
                                                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                                    }
                                }
                            }
                            justiceList.clear();
                            this.isDone = true;
                            break;
                        case "Hermit":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new HermitPower(AbstractDungeon.player)));
                            this.isDone = true;
                            break;
                        case "Fortune":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new FortunePower(AbstractDungeon.player)));
                            this.isDone = true;
                            break;
                        case "Strength":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new StrengthArcanaPower(AbstractDungeon.player)));
                            this.isDone = true;
                            break;
                        case "HangedMan":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new HangedManPower(AbstractDungeon.player)));
                            this.isDone = true;
                            break;
                        default:
                            this.isDone = true;
                            break;

                    }
                }

            }
        }
    }

    static {
        String[] uiText = {"The Arcana is the means by which all is revealed."};
        TEXT = uiText;
    }
}

