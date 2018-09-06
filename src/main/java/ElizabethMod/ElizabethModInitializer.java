package ElizabethMod;

import ElizabethMod.arcana.cards.*;
import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.cards.commonpersona.JackFrost;
import ElizabethMod.cards.special.WildCard;
import ElizabethMod.character.Elizabeth;
import ElizabethMod.enums.AbstractCardEnum;
import ElizabethMod.enums.ElizabethEnum;
import ElizabethMod.ui.screens.PersonaFusionScreen;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@SpireInitializer
public class ElizabethModInitializer implements EditCharactersSubscriber, EditCardsSubscriber, EditKeywordsSubscriber,
        EditStringsSubscriber {
    private static final String MODNAME = "Elizabeth";
    private static final String AUTHOR = "Reina";
    private static final String DESCRIPTION = "Adds Elizabeth as a playable character.";

    private static final Color VELVETBLUE = CardHelper.getColor(131.0f, 156.0f, 165.0f);
    private static final String ATTACK_VELVETBLUE = "ElizabethImgs/cardBG/bg_attack_velvetblue.png";
    private static final String SKILL_VELVETBLUE = "ElizabethImgs/cardBG/bg_skill_velvetblue.png";
    private static final String POWER_VELVETBLUE = "ElizabethImgs/cardBG/bg_power_velvetblue.png";
    private static final String ENERGY_ORB_VELVETBLUE = "ElizabethImgs/cardBG/card_velvetblue_orb.png";

    private static final String ATTACK_VELVETBLUE_PORTRAIT = "ElizabethImgs/cardBGStronk/bg_attack_velvetblue.png";
    private static final String SKILL_VELVETBLUE_PORTRAIT = "ElizabethImgs/cardBGStronk/bg_skill_velvetblue.png";
    private static final String POWER_VELVETBLUE_PORTRAIT = "ElizabethImgs/cardBGStronk/bg_power_velvetblue.png";
    private static final String ENERGY_ORB_VELVETBLUE_PORTRAIT = "ElizabethImgs/cardBGStronk/card_velvetblue_orb.png";
    private static final String Elizabeth_Portrait = "ElizabethImgs/char/ElizabethBG.jpg";
    private static final String Elizabeth_Button = "ElizabethImgs/char/ElizabethButton.png";

    public static ArrayList<AbstractArcanaCard> arcanaList = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> compendium = new ArrayList<>();
    public static ArrayList<AbstractCard> listOfBasicPersona = new ArrayList<>();
    public static PersonaFusionScreen personaFusionScreen = new PersonaFusionScreen();

    public ElizabethModInitializer() {
        BaseMod.subscribe(this);
        BaseMod.addColor(AbstractCardEnum.VELVET_BLUE,
                VELVETBLUE, VELVETBLUE, VELVETBLUE, VELVETBLUE, VELVETBLUE, VELVETBLUE, VELVETBLUE,
                ATTACK_VELVETBLUE, SKILL_VELVETBLUE, POWER_VELVETBLUE, ENERGY_ORB_VELVETBLUE,
                ATTACK_VELVETBLUE_PORTRAIT, SKILL_VELVETBLUE_PORTRAIT, POWER_VELVETBLUE_PORTRAIT,
                ENERGY_ORB_VELVETBLUE_PORTRAIT);
    }

    public static void initialize() {
        ElizabethModInitializer mod = new ElizabethModInitializer();
    }


    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(Elizabeth.class, "The Attendant", "ATTENDANT",
                AbstractCardEnum.VELVET_BLUE, "Elizabeth",
                Elizabeth_Button, Elizabeth_Portrait,
                ElizabethEnum.ATTENDANT);
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new WildCard());
        BaseMod.addCard(new JackFrost());
        listOfBasicPersona.add(new JackFrost());
        arcanaList.add(new Fool());
        arcanaList.add(new Magician());
        arcanaList.add(new Priestess());
        arcanaList.add(new Empress());
        arcanaList.add(new Emperor());
    }

    @Override
    public void receiveEditKeywords() {
        String[] Soulbound = {"soulbound"};
        BaseMod.addKeyword(Soulbound, "Cannot be removed from your hand.");
    }

    @Override
    public void receiveEditStrings() {
//        String relicStrings = Gdx.files.internal("localization/Yohane-RelicStrings-eng.json").readString(
//                String.valueOf(StandardCharsets.UTF_8));
//        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String powerStrings = Gdx.files.internal("localization/Elizabeth-Powerstrings-eng.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
        String cardStrings = Gdx.files.internal("localization/Elizabeth-Cardstrings-eng.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
    }
}
