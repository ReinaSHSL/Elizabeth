package ElizabethMod;

import ElizabethMod.arcana.cards.*;
import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.cards.commonpersona.Apsaras;
import ElizabethMod.cards.commonpersona.JackFrost;
import ElizabethMod.cards.special.WildCard;
import ElizabethMod.cards.uncommonpersona.QueenMab;
import ElizabethMod.character.Elizabeth;
import ElizabethMod.enums.AbstractCardEnum;
import ElizabethMod.enums.ElizabethEnum;
import ElizabethMod.relics.MemoriesOfYou;
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
import com.megacrit.cardcrawl.localization.RelicStrings;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

@SpireInitializer
public class ElizabethModInitializer implements EditCharactersSubscriber, EditCardsSubscriber, EditKeywordsSubscriber,
        EditStringsSubscriber, EditRelicsSubscriber {
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
    public static ArrayList<AbstractPersonaCard> listOfFoolPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfMagicianPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfPriestessPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfEmpressPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfEmperorPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfHierophantPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfLoversPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfChariotPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfJusticePersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfHermitPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfFortunePersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfStrengthPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfHangedManPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfDeathPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfTemperancePersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfDevilPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfTowerPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfStarPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfMoonPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfSunPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfJudgementPersona = new ArrayList<>();
    public static ArrayList<AbstractPersonaCard> listOfUniversePersona = new ArrayList<>();

    public static ArrayList<AbstractCard> listOfUncommonPersona = new ArrayList<>();
    public static PersonaFusionScreen personaFusionScreen = new PersonaFusionScreen();
    public static HashMap<String, String> personaFusionCombinations = new HashMap<>();

    public ElizabethModInitializer() {
        BaseMod.subscribe(this);
        BaseMod.addColor(AbstractCardEnum.VELVET_BLUE,
                VELVETBLUE, VELVETBLUE, VELVETBLUE, VELVETBLUE, VELVETBLUE, VELVETBLUE, VELVETBLUE,
                ATTACK_VELVETBLUE, SKILL_VELVETBLUE, POWER_VELVETBLUE, ENERGY_ORB_VELVETBLUE,
                ATTACK_VELVETBLUE_PORTRAIT, SKILL_VELVETBLUE_PORTRAIT, POWER_VELVETBLUE_PORTRAIT,
                ENERGY_ORB_VELVETBLUE_PORTRAIT);
        populateMap();
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
        //Non-Persona Cards
        BaseMod.addCard(new WildCard());

        //Common Persona
        BaseMod.addCard(new Apsaras());
        BaseMod.addCard(new JackFrost());

        //Uncommon Persona
        BaseMod.addCard(new QueenMab());

        //Rare Persona

        //List of Fools


        //List of Magicians
        listOfMagicianPersona.add(new JackFrost());

        //List of Priestesses
        listOfPriestessPersona.add(new Apsaras());

        //List of Lovers
        listOfLoversPersona.add(new QueenMab());

        //List of Basic Persona
        listOfBasicPersona.add(new JackFrost());
        listOfBasicPersona.add(new Apsaras());

        //List of Uncommon Persona
        listOfUncommonPersona.add(new QueenMab());

        //List of Arcana
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
        String[] Frozen = {"freeze"};
        BaseMod.addKeyword(Frozen, "Enemy cannot act this turn. Removed upon being attacked.");
        String[] Bonus = {"bonus"};
        BaseMod.addKeyword(Bonus, "Additional effects activated by having the same Arcana as the Persona.");
    }

    @Override
    public void receiveEditStrings() {
        String relicStrings = Gdx.files.internal("localization/Elizabeth-RelicStrings-eng.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String powerStrings = Gdx.files.internal("localization/Elizabeth-Powerstrings-eng.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
        String cardStrings = Gdx.files.internal("localization/Elizabeth-Cardstrings-eng.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new MemoriesOfYou(), AbstractCardEnum.VELVET_BLUE);
    }

    private void populateMap() {
        personaFusionCombinations.put("FoolFool", "Fool");
        personaFusionCombinations.put("FoolMagician", "Hierophant");
        personaFusionCombinations.put("FoolPriestess", "Justice");
        personaFusionCombinations.put("FoolEmpress", "Fortune");
        personaFusionCombinations.put("FoolEmperor", "Chariot");
        personaFusionCombinations.put("FoolHierophant", "Hermit");
        personaFusionCombinations.put("FoolLovers", "Priestess");
        personaFusionCombinations.put("FoolChariot", "Emperor");
        personaFusionCombinations.put("FoolJustice", "Lovers");
        personaFusionCombinations.put("FoolHermit", "Priestess");
        personaFusionCombinations.put("FoolFortune", "Justice");
        personaFusionCombinations.put("FoolStrength", "HangedMan");
        personaFusionCombinations.put("FoolHangedMan", "Magician");
        personaFusionCombinations.put("FoolDeath", "Strength");
        personaFusionCombinations.put("FoolTemperance", "Hierophant");
        personaFusionCombinations.put("FoolDevil", "Hermit");
        personaFusionCombinations.put("FoolTower", "Moon");
        personaFusionCombinations.put("FoolStar", "Judgement");
        personaFusionCombinations.put("FoolMoon", "Fortune");
        personaFusionCombinations.put("FoolSun", "Empress");
        personaFusionCombinations.put("FoolJudgement", "Star");
        personaFusionCombinations.put("MagicianMagician", "Magician");
        personaFusionCombinations.put("MagicianPriestess", "Lovers");
        personaFusionCombinations.put("MagicianEmpress", "HangedMan");
        personaFusionCombinations.put("MagicianEmperor", "Temperance");
        personaFusionCombinations.put("MagicianHierophant", "Hermit");
        personaFusionCombinations.put("MagicianLovers", "Emperor");
        personaFusionCombinations.put("MagicianChariot", "Devil");
        personaFusionCombinations.put("MagicianJustice", "Hierophant");
        personaFusionCombinations.put("MagicianHermit", "Chariot");
        personaFusionCombinations.put("MagicianFortune", "Emperor");
        personaFusionCombinations.put("MagicianStrength", "null");
        personaFusionCombinations.put("MagicianHangedMan", "Devil");
        personaFusionCombinations.put("MagicianDeath", "null");
        personaFusionCombinations.put("MagicianTemperance", "Death");
        personaFusionCombinations.put("MagicianDevil", "Temperance");
        personaFusionCombinations.put("MagicianTower", "Empress");
        personaFusionCombinations.put("MagicianStar", "Empress");
        personaFusionCombinations.put("MagicianMoon", "Priestess");
        personaFusionCombinations.put("MagicianSun", "Empress");
        personaFusionCombinations.put("MagicianJudgement", "Death");
        personaFusionCombinations.put("PriestessPriestess", "Priestess");
        personaFusionCombinations.put("PriestessEmpress", "Lovers");
        personaFusionCombinations.put("PriestessEmperor", "Justice");
        personaFusionCombinations.put("PriestessHierophant", "Chariot");
        personaFusionCombinations.put("PriestessLovers", "Magician");
        personaFusionCombinations.put("PriestessChariot", "Magician");
        personaFusionCombinations.put("PriestessJustice", "Lovers");
        personaFusionCombinations.put("PriestessHermit", "Strength");
        personaFusionCombinations.put("PriestessFortune", "Magician");
        personaFusionCombinations.put("PriestessStrength", "Hermit");
        personaFusionCombinations.put("PriestessHangedMan", "Strength");
        personaFusionCombinations.put("PriestessDeath", "Emperor");
        personaFusionCombinations.put("PriestessTemperance", "Empress");
        personaFusionCombinations.put("PriestessDevil", "null");
        personaFusionCombinations.put("PriestessTower", "null");
        personaFusionCombinations.put("PriestessStar", "Judgement");
        personaFusionCombinations.put("PriestessMoon", "Star");
        personaFusionCombinations.put("PriestessSun", "Star");
        personaFusionCombinations.put("PriestessJudgement", "Empress");
        personaFusionCombinations.put("EmpressEmpress", "Empress");
        personaFusionCombinations.put("EmpressEmperor", "Lovers");
        personaFusionCombinations.put("EmpressHierophant", "Priestess");
        personaFusionCombinations.put("EmpressLovers", "Fortune");
        personaFusionCombinations.put("EmpressChariot", "Devil");
        personaFusionCombinations.put("EmpressJustice", "Emperor");
        personaFusionCombinations.put("EmpressHermit", "Lovers");
        personaFusionCombinations.put("EmpressFortune", "Strength");
        personaFusionCombinations.put("EmpressStrength", "Chariot");
        personaFusionCombinations.put("EmpressHangedMan", "Chariot");
        personaFusionCombinations.put("EmpressDeath", "Lovers");
        personaFusionCombinations.put("EmpressTemperance", "Lovers");
        personaFusionCombinations.put("EmpressDevil", "Lovers");
        personaFusionCombinations.put("EmpressTower", "Chariot");
        personaFusionCombinations.put("EmpressStar", "Temperance");
        personaFusionCombinations.put("EmpressMoon", "Lovers");
        personaFusionCombinations.put("EmpressSun", "Lovers");
        personaFusionCombinations.put("EmpressJudgement", "null");
        personaFusionCombinations.put("EmperorEmperor", "Emperor");
        personaFusionCombinations.put("EmperorHierophant", "Chariot");
        personaFusionCombinations.put("EmperorLovers", "Chariot");
        personaFusionCombinations.put("EmperorChariot", "Hermit");
        personaFusionCombinations.put("EmperorJustice", "Devil");
        personaFusionCombinations.put("EmperorHermit", "Strength");
        personaFusionCombinations.put("EmperorFortune", "null");
        personaFusionCombinations.put("EmperorStrength", "HangedMan");
        personaFusionCombinations.put("EmperorHangedMan", "Hermit");
        personaFusionCombinations.put("EmperorDeath", "Moon");
        personaFusionCombinations.put("EmperorTemperance", "HangedMan");
        personaFusionCombinations.put("EmperorDevil", "null");
        personaFusionCombinations.put("EmperorTower", "null");
        personaFusionCombinations.put("EmperorStar", "Justice");
        personaFusionCombinations.put("EmperorMoon", "null");
        personaFusionCombinations.put("EmperorSun", "Empress");
        personaFusionCombinations.put("EmperorJudgement", "Hierophant");
        personaFusionCombinations.put("HierophantHierophant", "Hierophant");
        personaFusionCombinations.put("HierophantLovers", "Magician");
        personaFusionCombinations.put("HierophantChariot", "Justice");
        personaFusionCombinations.put("HierophantJustice", "Chariot");
        personaFusionCombinations.put("HierophantHermit", "Chariot");
        personaFusionCombinations.put("HierophantFortune", "Emperor");
        personaFusionCombinations.put("HierophantStrength", "Priestess");
        personaFusionCombinations.put("HierophantHangedMan", "Lovers");
        personaFusionCombinations.put("HierophantDeath", "Empress");
        personaFusionCombinations.put("HierophantTemperance", "Strength");
        personaFusionCombinations.put("HierophantDevil", "null");
        personaFusionCombinations.put("HierophantTower", "Temperance");
        personaFusionCombinations.put("HierophantStar", "Priestess");
        personaFusionCombinations.put("HierophantMoon", "Temperance");
        personaFusionCombinations.put("HierophantSun", "Temperance");
        personaFusionCombinations.put("HierophantJudgement", "Lovers");
        personaFusionCombinations.put("LoversLovers", "Lovers");
        personaFusionCombinations.put("LoversChariot", "Emperor");
        personaFusionCombinations.put("LoversJustice", "Chariot");
        personaFusionCombinations.put("LoversHermit", "Justice");
        personaFusionCombinations.put("LoversFortune", "Magician");
        personaFusionCombinations.put("LoversStrength", "Hierophant");
        personaFusionCombinations.put("LoversHangedMan", "Hermit");
        personaFusionCombinations.put("LoversDeath", "Devil");
        personaFusionCombinations.put("LoversTemperance", "Priestess");
        personaFusionCombinations.put("LoversDevil", "Strength");
        personaFusionCombinations.put("LoversTower", "Star");
        personaFusionCombinations.put("LoversStar", "Hierophant");
        personaFusionCombinations.put("LoversMoon", "Empress");
        personaFusionCombinations.put("LoversSun", "Hierophant");
        personaFusionCombinations.put("LoversJudgement", "Lovers");
        personaFusionCombinations.put("ChariotChariot", "Chariot");
        personaFusionCombinations.put("ChariotJustice", "Magician");
        personaFusionCombinations.put("ChariotHermit", "Temperance");
        personaFusionCombinations.put("ChariotFortune", "Strength");
        personaFusionCombinations.put("ChariotStrength", "Justice");
        personaFusionCombinations.put("ChariotHangedMan", "Fortune");
        personaFusionCombinations.put("ChariotDeath", "null");
        personaFusionCombinations.put("ChariotTemperance", "Death");
        personaFusionCombinations.put("ChariotDevil", "HangedMan");
        personaFusionCombinations.put("ChariotTower", "Moon");
        personaFusionCombinations.put("ChariotStar", "null");
        personaFusionCombinations.put("ChariotMoon", "Fortune");
        personaFusionCombinations.put("ChariotSun", "null");
        personaFusionCombinations.put("ChariotJudgement", "null");
        personaFusionCombinations.put("JusticeJustice", "Justice");
        personaFusionCombinations.put("JusticeHermit", "Priestess");
        personaFusionCombinations.put("JusticeFortune", "Chariot");
        personaFusionCombinations.put("JusticeStrength", "Temperance");
        personaFusionCombinations.put("JusticeHangedMan", "Priestess");
        personaFusionCombinations.put("JusticeDeath", "Moon");
        personaFusionCombinations.put("JusticeTemperance", "Moon");
        personaFusionCombinations.put("JusticeDevil", "null");
        personaFusionCombinations.put("JusticeTower", "Star");
        personaFusionCombinations.put("JusticeStar", "Emperor");
        personaFusionCombinations.put("JusticeMoon", "null");
        personaFusionCombinations.put("JusticeSun", "Emperor");
        personaFusionCombinations.put("JusticeJudgement", "Judgement");
        personaFusionCombinations.put("HermitHermit", "Hermit");
        personaFusionCombinations.put("HermitFortune", "Emperor");
        personaFusionCombinations.put("HermitStrength", "Fortune");
        personaFusionCombinations.put("HermitHangedMan", "Fortune");
        personaFusionCombinations.put("HermitDeath", "null");
        personaFusionCombinations.put("HermitTemperance", "HangedMan");
        personaFusionCombinations.put("HermitDevil", "Death");
        personaFusionCombinations.put("HermitTower", "null");
        personaFusionCombinations.put("HermitStar", "Chariot");
        personaFusionCombinations.put("HermitMoon", "Magician");
        personaFusionCombinations.put("HermitSun", "null");
        personaFusionCombinations.put("HermitJudgement", "null");
        personaFusionCombinations.put("FortuneFortune", "Fortune");
        personaFusionCombinations.put("FortuneStrength", "null");
        personaFusionCombinations.put("FortuneHangedMan", "Strength");
        personaFusionCombinations.put("FortuneDeath", "null");
        personaFusionCombinations.put("FortuneTemperance", "Lovers");
        personaFusionCombinations.put("FortuneDevil", "Moon");
        personaFusionCombinations.put("FortuneTower", "Moon");
        personaFusionCombinations.put("FortuneStar", "Moon");
        personaFusionCombinations.put("FortuneMoon", "Chariot");
        personaFusionCombinations.put("FortuneSun", "Temperance");
        personaFusionCombinations.put("FortuneJudgement", "null");
        personaFusionCombinations.put("StrengthStrength", "Strength");
        personaFusionCombinations.put("StrengthHangedMan", "Hermit");
        personaFusionCombinations.put("StrengthDeath", "HangedMan");
        personaFusionCombinations.put("StrengthTemperance", "Moon");
        personaFusionCombinations.put("StrengthDevil", "Fortune");
        personaFusionCombinations.put("StrengthTower", "Devil");
        personaFusionCombinations.put("StrengthStar", "Priestess");
        personaFusionCombinations.put("StrengthMoon", "HangedMan");
        personaFusionCombinations.put("StrengthSun", "Priestess");
        personaFusionCombinations.put("StrengthJudgement", "HangedMan");
        personaFusionCombinations.put("HangedManHangedMan", "HangedMan");
        personaFusionCombinations.put("HangedManDeath", "Devil");
        personaFusionCombinations.put("HangedManTemperance", "Hierophant");
        personaFusionCombinations.put("HangedManDevil", "Moon");
        personaFusionCombinations.put("HangedManTower", "Death");
        personaFusionCombinations.put("HangedManStar", "Strength");
        personaFusionCombinations.put("HangedManMoon", "Empress");
        personaFusionCombinations.put("HangedManSun", "null");
        personaFusionCombinations.put("HangedManJudgement", "null");
        personaFusionCombinations.put("DeathDeath", "Death");
        personaFusionCombinations.put("DeathTemperance", "null");
        personaFusionCombinations.put("DeathDevil", "null");
        personaFusionCombinations.put("DeathTower", "null");
        personaFusionCombinations.put("DeathStar", "null");
        personaFusionCombinations.put("DeathMoon", "Star");
        personaFusionCombinations.put("DeathSun", "null");
        personaFusionCombinations.put("DeathJudgement", "null");
        personaFusionCombinations.put("TemperanceTemperance", "Temperance");
        personaFusionCombinations.put("TemperanceDevil", "Death");
        personaFusionCombinations.put("TemperanceTower", "Devil");
        personaFusionCombinations.put("TemperanceStar", "Moon");
        personaFusionCombinations.put("TemperanceMoon", "Empress");
        personaFusionCombinations.put("TemperanceSun", "Star");
        personaFusionCombinations.put("TemperanceJudgement", "Moon");
        personaFusionCombinations.put("DevilDevil", "Devil");
        personaFusionCombinations.put("DevilTower", "null");
        personaFusionCombinations.put("DevilStar", "null");
        personaFusionCombinations.put("DevilMoon", "null");
        personaFusionCombinations.put("DevilSun", "null");
        personaFusionCombinations.put("DevilJudgement", "null");
        personaFusionCombinations.put("TowerTower", "Tower");
        personaFusionCombinations.put("TowerStar", "null");
        personaFusionCombinations.put("TowerMoon", "Fortune");
        personaFusionCombinations.put("TowerSun", "null");
        personaFusionCombinations.put("TowerJudgement", "Judgement");
        personaFusionCombinations.put("StarStar", "Star");
        personaFusionCombinations.put("StarMoon", "Death");
        personaFusionCombinations.put("StarSun", "Justice");
        personaFusionCombinations.put("StarJudgement", "Temperance");
        personaFusionCombinations.put("MoonMoon", "Moon");
        personaFusionCombinations.put("MoonSun", "Temperance");
        personaFusionCombinations.put("MoonJudgement", "null");
        personaFusionCombinations.put("SunSun", "Sun");
        personaFusionCombinations.put("SunJudgement", "Star");
        personaFusionCombinations.put("JudgementJudgement", "Judgement");
        personaFusionCombinations.put("UniverseUniverse", "Universe");
    }
}
