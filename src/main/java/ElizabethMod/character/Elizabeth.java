package ElizabethMod.character;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.arcana.cards.Death;
import ElizabethMod.cards.special.WildCard;
import ElizabethMod.enums.ElizabethEnum;
import ElizabethMod.powers.CharmPower;
import basemod.BaseMod;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.daily.DailyMods;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;

public class Elizabeth extends CustomPlayer implements OnStartBattleSubscriber, PostBattleSubscriber {
    public static final int ENERGY_PER_TURN = 3;
    public static final String MY_CHARACTER_SHOULDER_2 = "ElizabethImgs/char/shoulder2.png"; // campfire pose
    public static final String MY_CHARACTER_SHOULDER_1 = "ElizabethImgs/char/shoulder.png"; // another campfire pose
    public static final String MY_CHARACTER_CORPSE = "ElizabethImgs/char/corpse.png"; // dead corpse
    public static final String MY_CHARACTER_ANIMATION = "ElizabethImgs/char/animation.scml"; // spriter animation
    private boolean containsDeath = false;

    public Elizabeth (String name, PlayerClass setClass) {
        super(name, setClass, null, null, null, new SpriterAnimation(MY_CHARACTER_ANIMATION));
        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        initializeClass(null, MY_CHARACTER_SHOULDER_2, // required call to load textures and setup energy/loadout
                MY_CHARACTER_SHOULDER_1,
                MY_CHARACTER_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        if (Settings.dailyModsEnabled() && DailyMods.cardMods.get("Diverse")) {
            this.masterMaxOrbs = 1;
        }
        BaseMod.subscribe(this);
    }

    public static ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Elizabeth:JackFrost");
        retVal.add("Elizabeth:Apsaras");
        return retVal;
    }

    public static ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Elizabeth:MemoriesOfYou");
        return retVal;
    }

    public static final int STARTING_HP = 80;
    public static final int MAX_HP = 50;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 5;


    public static CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
        return new CharSelectInfo("Elizabeth", "An attendant of the Velvet Room. NL Uses Persona fusion and magic to ascend through the Spire.",
                STARTING_HP, MAX_HP, 0, STARTING_GOLD, HAND_SIZE,
                ElizabethEnum.ATTENDANT, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        if (AbstractDungeon.player instanceof Elizabeth && !AbstractDungeon.player.hand.group.contains(new WildCard())) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new WildCard()));
        }
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        for (AbstractCard c : ElizabethModInitializer.arcanaList) {
            if (c.cardID.equals(Death.ID)) {
                containsDeath = true;
            }
        }
        if (!containsDeath) {
            ElizabethModInitializer.arcanaList.add(13, new Death());
        }
    }

    @Override
    public void damage(DamageInfo info) {
        AbstractMonster owner;
        if(info.owner instanceof  AbstractMonster && info.type == DamageInfo.DamageType.NORMAL) {
            owner = (AbstractMonster) info.owner;
            if (owner.hasPower(CharmPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(owner, new DamageInfo(owner, info.base, info.type),
                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            } else {
                super.damage(info);
            }
        }
        else {
            super.damage(info);
        }
    }
}
