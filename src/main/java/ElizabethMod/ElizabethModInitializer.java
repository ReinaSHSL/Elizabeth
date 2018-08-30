package ElizabethMod;

import ElizabethMod.character.Elizabeth;
import ElizabethMod.enums.AbstractCardEnum;
import ElizabethMod.enums.ElizabethEnum;
import basemod.BaseMod;
import basemod.interfaces.EditCharactersSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.CardHelper;

@SpireInitializer
public class ElizabethModInitializer implements EditCharactersSubscriber {
    private static final String MODNAME = "Elizabeth";
    private static final String AUTHOR = "Reina";
    private static final String DESCRIPTION = "Adds Elizabeth as a playable character.";

    private static final Color VELVETBLUE = CardHelper.getColor(131.0f, 156.0f, 165.0f);
    private static final String ATTACK_VELVETBLUE = "cardBG/bg_attack_velvetblue.png";
    private static final String SKILL_VELVETBLUE = "cardBG/bg_skill_velvetblue.png";
    private static final String POWER_VELVETBLUE = "cardBG/bg_power_velvetblue.png";
    private static final String ENERGY_ORB_VELVETBLUE = "cardBG/card_velvetblue_orb.png";

    private static final String ATTACK_VELVETBLUE_PORTRAIT = "cardBGStronk/bg_attack_velvetblue.png";
    private static final String SKILL_VELVETBLUE_PORTRAIT = "cardBGStronk/bg_skill_velvetblue.png";
    private static final String POWER_VELVETBLUE_PORTRAIT = "cardBGStronk/bg_power_velvetblue.png";
    private static final String ENERGY_ORB_VELVETBLUE_PORTRAIT = "cardBGStronk/card_velvetblue_orb.png";
    private static final String Elizabeth_Portrait = "char/ElizabethBG.jpg";
    private static final String Elizabeth_Button = "char/ElizabethButton.png";

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
        BaseMod.addCharacter(Elizabeth.class,  "The Attendant", "ATTENDANT",
                AbstractCardEnum.VELVET_BLUE, "Elizabeth",
                Elizabeth_Button , Elizabeth_Portrait,
                ElizabethEnum.ATTENDANT);
    }
}
