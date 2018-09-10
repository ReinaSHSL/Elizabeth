package ElizabethMod.actions;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.arcana.cards.AbstractArcanaCard;
import ElizabethMod.arcana.cards.Chariot;
import ElizabethMod.arcana.cards.Death;
import ElizabethMod.arcana.powers.*;
import ElizabethMod.powers.*;
import basemod.interfaces.PostBattleSubscriber;
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
import com.megacrit.cardcrawl.powers.NoBlockPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;
import java.util.Iterator;

public class ArcanaSwapAction extends AbstractGameAction implements PostBattleSubscriber {
    private static String[] TEXT;
    private AbstractPlayer p;
    private AbstractMonster m = null;
    private ArrayList<JusticeDamagePower> justiceList = new ArrayList<>();
    private boolean towerDupe = true;

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
                                    new FoolPower(p)));
                            this.isDone = true;
                            break;
                        case "Magician":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new MagicianPower(p)));
                            this.isDone = true;
                            break;
                        case "Priestess":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new PriestessPower(p)));
                            this.isDone = true;
                            break;
                        case "Empress":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new EmpressPower(p)));
                            this.isDone = true;
                            break;
                        case "Emperor":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new EmperorPower(p)));
                            new TargetAction("Emperor", 1);
                            this.isDone = true;
                            break;
                        case "Hierophant":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new HierophantPower(p)));
                            this.isDone = true;
                            break;
                        case "Lovers":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new LoversPower(p)));
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new LoversVulnerablePower(p, 1, false)));
                            new TargetAction("Lovers", 1);
                            this.isDone = true;
                            break;
                        case "Chariot":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new ChariotPower(p)));
                            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                                AbstractDungeon.actionManager.addToBottom(new DamageAction(mo,
                                        new DamageInfo(p, 3, DamageInfo.DamageType.NORMAL),
                                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                            }
                            this.isDone = true;
                            break;
                        case "Justice":
                            for (AbstractPower po : p.powers) {
                                if (po.ID.equals(JusticeDamagePower.POWER_ID)) {
                                    justiceList.add((JusticeDamagePower) po);
                                }
                            }
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new JusticePower(p)));
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
                                    new HermitPower(p)));
                            this.isDone = true;
                            break;
                        case "Fortune":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new FortunePower(p)));
                            this.isDone = true;
                            break;
                        case "Strength":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new StrengthArcanaPower(p)));
                            this.isDone = true;
                            break;
                        case "HangedMan":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new InevitabilityPower(p, 0)));
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new HangedManPower(p)));
                            this.isDone = true;
                            break;
                        case "Death":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new DeathPower(p)));
                            ElizabethModInitializer.arcanaList.removeIf(ca -> ca.getClass().equals(Death.class));
                            this.isDone = true;
                            break;
                        case "Temperance":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new TemperancePower(p)));
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new ModerationPower(p)));
                            this.isDone = true;
                            break;
                        case "Devil":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new DevilPower(p)));
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new NoBlockPower(p, 99, false)));
                            this.isDone = true;
                            break;
                        case "Tower":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new TowerPower(p)));
                            if (this.towerDupe) {
                                AbstractDungeon.actionManager.addToBottom(new DuplicateRandomEnemyAction());
                            }
                            this.towerDupe = false;
                            this.isDone = true;
                            break;
                        case "Star":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new StarPower(p)));
                            this.isDone = true;
                            break;
                        case "Moon":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new MoonPower(p)));
                            AbstractDungeon.actionManager.addToBottom(new MoveToTopOfDeckAction());
                            this.isDone = true;
                            break;
                        case "Sun":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new SunPower(p)));
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new IcarusPower(p)));
                            this.isDone = true;
                            break;
                        case "Judgement":
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                                    new JudgementPower(p)));
                            AbstractDungeon.actionManager.addToBottom(new EqualizeHPAction());
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

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        this.towerDupe = true;
    }
}

