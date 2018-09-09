package ElizabethMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.exordium.*;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DuplicateRandomEnemyAction extends AbstractGameAction {
    public DuplicateRandomEnemyAction() {
        this.duration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            float posX = 0;
            AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.getRandomMonster();
            float posY = 0;
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (mo.drawX != 0 && mo.drawX < posX) {
                    posX = mo.drawX;
                }
            }
            switch(m.id) {
                case AwakenedOne.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new AwakenedOne(posX - 300F, posY), false));
                    break;
                case Darkling.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Darkling(posX - 300F, posY), false));
                    break;
                case Deca.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Deca(), false));
                    m.drawX -= 300F;
                    break;
                case Donu.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Donu(), false));
                    m.drawX -= 300F;
                    break;
                case Exploder.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Exploder(posX - 300F, posY), false));
                    break;
                case GiantHead.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new GiantHead(), false));
                    m.drawX -= 300F;
                    break;
                case Maw.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Maw(posX - 300F, posY), false));
                    break;
                case Nemesis.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Nemesis(), false));
                    m.drawX -= 400F;
                    break;
                case OrbWalker.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new OrbWalker(posX - 300F, posY), false));
                    break;
                case Reptomancer.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Reptomancer(), false));
                    m.drawX -= 200F;
                    break;
                case Repulsor.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Repulsor(posX - 300F, posY), false));
                    break;
                case SnakeDagger.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SnakeDagger(posX - 300F, posY), true));
                    break;
                case Spiker.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Spiker(posX - 300F, posY), false));
                    break;
                case SpireGrowth.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SpireGrowth(), false));
                    m.drawX -= 400F;
                    break;
                case TimeEater.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new TimeEater(), false));
                    m.drawX -= 300F;
                    break;
                case Transient.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Transient(), false));
                    m.drawX -= 300F;
                    break;
                case BanditBear.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new BanditBear(posX - 300F, posY), false));
                    break;
                case BanditLeader.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new BanditLeader(posX - 300F, posY), false));
                    break;
                case BanditPointy.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new BanditPointy(posX - 300F, posY), false));
                    break;
                case BookOfStabbing.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new BookOfStabbing(), false));
                    m.drawX -= 300F;
                    break;
                case BronzeAutomaton.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new BronzeAutomaton(), false));
                    m.drawX -= 300F;
                    break;
                case BronzeOrb.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new BronzeOrb(posX - 300F, posY, 0), false));
                    break;
                case Byrd.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Byrd(posX - 300F, posY), false));
                    break;
                case Centurion.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Centurion(posX - 300F, posY), false));
                    break;
                case Champ.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Champ(), false));
                    m.drawX -= 400F;
                    break;
                case Chosen.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Chosen(posX - 300F, posY), false));
                    break;
                case GremlinLeader.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new GremlinLeader(), false));
                    m.drawX -= 200F;
                    break;
                case Healer.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Healer(posX - 300F, posY), false));
                    break;
                case Mugger.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Mugger(posX - 300F, posY), false));
                    break;
                case ShelledParasite.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new ShelledParasite(posX - 300F, posY), false));
                    break;
                case SnakePlant.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SnakePlant(posX - 300F, posY), false));
                    break;
                case Snecko.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Snecko(posX - 300F, posY), false));
                    break;
                case SphericGuardian.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SphericGuardian(posX - 300F, posY), false));
                    break;
                case Taskmaster.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Taskmaster(posX - 300F, posY), false));
                    break;
                case TheCollector.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new TheCollector(), false));
                    m.drawX -= 300F;
                    break;
                case TorchHead.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new TorchHead(posX - 300F, posY), false));
                    break;
                case AcidSlime_L.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new AcidSlime_L(posX - 300F, posY), false));
                    break;
                case AcidSlime_M.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new AcidSlime_M(posX - 300F, posY), false));
                    break;
                case AcidSlime_S.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new AcidSlime_S(posX - 300F, posY, 0), false));
                    break;
                case Cultist.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Cultist(posX - 300F, posY), false));
                    break;
                case FungiBeast.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new FungiBeast(posX - 300F, posY), false));
                    break;
                case GremlinFat.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new GremlinFat(posX - 300F, posY), false));
                    break;
                case GremlinNob.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new GremlinNob(posX - 300F, posY), false));
                    break;
                case GremlinThief.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new GremlinThief(posX - 300F, posY), false));
                    break;
                case GremlinTsundere.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new GremlinTsundere(posX - 300F, posY), false));
                    break;
                case GremlinWarrior.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new GremlinWarrior(posX - 300F, posY), false));
                    break;
                case GremlinWizard.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new GremlinWizard(posX - 300F, posY), false));
                    break;
                case Hexaghost.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Hexaghost(), false));
                    m.drawX -= 300F;
                    break;
                case JawWorm.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new JawWorm(posX - 300F, posY), false));
                    break;
                case Lagavulin.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Lagavulin(true), false));
                    m.drawX -= 400F;
                    break;
                case Looter.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Looter(posX - 300F, posY), false));
                    break;
                case LouseDefensive.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new LouseDefensive(posX - 300F, posY), false));
                    break;
                case LouseNormal.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new LouseNormal(posX - 300F, posY), false));
                    break;
                case Sentry.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new Sentry(posX - 500F, posY), false));
                    break;
                case SlaverBlue.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SlaverBlue(posX - 300F, posY), false));
                    break;
                case SlaverRed.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SlaverRed(posX - 300F, posY), false));
                    break;
                case SlimeBoss.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SlimeBoss(), false));
                    m.drawX -= 300F;
                    break;
                case SpikeSlime_L.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SpikeSlime_L(posX - 300F, posY), false));
                    break;
                case SpikeSlime_M.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SpikeSlime_M(posX - 300F, posY), false));
                    break;
                case SpikeSlime_S.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SpikeSlime_S(posX - 300F, posY, 0), false));
                    break;
                case TheGuardian.ID:
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new TheGuardian(), false));
                    m.drawX -= 400F;
                    break;
                default:
                    AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.0F, "Contact the developer about a bug!", this.source.isPlayer));
            }
            this.isDone = true;
        }
    }
}
