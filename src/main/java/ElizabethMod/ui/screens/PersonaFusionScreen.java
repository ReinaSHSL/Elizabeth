package ElizabethMod.ui.screens;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.cards.screencards.BlankPersonaCard;
import ElizabethMod.character.Elizabeth;
import ElizabethMod.patches.ScreenStatePatch;
import ElizabethMod.tools.DataParser;
import ElizabethMod.tools.InvalidCommandException;
import ElizabethMod.tools.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.Strike_Blue;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class PersonaFusionScreen {

    private ArrayList<Hitbox> hbs = new ArrayList<Hitbox>();
    private boolean justClicked = false;
    private float yScale;
    public boolean openedDuringReward;
    private static Hitbox personaOne;
    private static Hitbox personaTwo;
    private static Hitbox personaResult;
    private static AbstractPersonaCard personaOneCard;
    private static AbstractPersonaCard personaTwoCard;
    private static AbstractPersonaCard personaResultCard;
    private boolean personaChange = false;
    private boolean changePersonaOne = false;
    private boolean changePersonaTwo = false;
    private static final File dataFile = new File("resources/PersonaFusion.txt");

    public PersonaFusionScreen() {
    }

    public void open(boolean resetCards) {
        if (resetCards) {
            personaOneCard = new BlankPersonaCard();
            personaTwoCard = new BlankPersonaCard();
            personaResultCard = new BlankPersonaCard();
        }
        personaOneCard.drawScale = 1.0f;
        personaOneCard.current_x = Settings.WIDTH / 4F * Settings.scale;
        personaOneCard.current_y = (Settings.HEIGHT / 2F - 200F) * Settings.scale;
        personaOne = new Hitbox(personaOneCard.current_x - (AbstractCard.IMG_WIDTH / 2), personaOneCard.current_y - (AbstractCard.IMG_HEIGHT / 2),
                AbstractCard.IMG_WIDTH, AbstractCard.IMG_HEIGHT);
        personaTwoCard.drawScale = 1.0f;
        personaTwoCard.current_x = Settings.WIDTH / 1.35F * Settings.scale;
        personaTwoCard.current_y = (Settings.HEIGHT / 2F - 200F) * Settings.scale;
        personaTwo = new Hitbox(personaTwoCard.current_x - (AbstractCard.IMG_WIDTH / 2), personaTwoCard.current_y - (AbstractCard.IMG_HEIGHT / 2),
                AbstractCard.IMG_WIDTH, AbstractCard.IMG_HEIGHT);
        personaResultCard.drawScale = 1.0f;
        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
        personaResult = new Hitbox(personaResultCard.current_x - (AbstractCard.IMG_WIDTH / 2), personaResultCard.current_y - (AbstractCard.IMG_HEIGHT / 2),
                AbstractCard.IMG_WIDTH, AbstractCard.IMG_HEIGHT);
        AbstractDungeon.player.releaseCard();
        AbstractDungeon.screen = ScreenStatePatch.PERSONA_FUSION_SCREEN;
        AbstractDungeon.overlayMenu.showBlackScreen();
        AbstractDungeon.overlayMenu.proceedButton.hide();
        AbstractDungeon.overlayMenu.cancelButton.show("Return");
        AbstractDungeon.isScreenUp = true;
        if (MathUtils.randomBoolean()) {
            CardCrawlGame.sound.play("MAP_OPEN", 0.1f);
        } else {
            CardCrawlGame.sound.play("MAP_OPEN_2", 0.1f);
        }
        hbs.clear();
        this.yScale = 0.0f;
    }

    public void close() {
        AbstractDungeon.overlayMenu.cancelButton.hide();
        if (MathUtils.randomBoolean()) {
            CardCrawlGame.sound.play("MAP_OPEN", 0.1f);
        } else {
            CardCrawlGame.sound.play("MAP_OPEN_2", 0.1f);
        }
    }

    public void render(SpriteBatch sb) {
        Texture velvetRoom = TextureLoader.getTexture("ElizabethImgs/ui/screens/velvetRoom.png");
        sb.setColor(Color.WHITE);
        yScale = MathHelper.scaleLerpSnap(yScale, 1.0f);
        sb.draw(velvetRoom, 0, 0);
        renderCardPreview(sb);
        justClicked = false;
    }

    private void renderCardPreview(SpriteBatch sb) {
        personaOneCard.render(sb);
        personaOne.render(sb);
        personaTwoCard.render(sb);
        personaTwo.render(sb);
        personaResultCard.render(sb);
        personaResult.render(sb);
    }

    public void update() {
        personaOne.update();
        personaTwo.update();
        getResultPersona();
        if (InputHelper.justClickedLeft) {
            justClicked = true;
        }
        if (personaOne.hovered) {
            updateCardPreview(personaOneCard);
        }
        if (personaTwo.hovered) {
            updateCardPreview(personaTwoCard);
        }
        if (personaResult.hovered) {
            updateCardPreview(personaResultCard);
        }
        if (personaOne.hovered && InputHelper.justClickedLeft) {
            changePersonaOne = true;
            openPersonaList();
        }
        if (personaTwo.hovered && InputHelper.justClickedLeft) {
            changePersonaTwo = true;
            openPersonaList();
        }
        if (personaOne.hovered && InputHelper.justClickedRight) {
            personaOneCard = new BlankPersonaCard();
            personaOneCard.drawScale = 1.0f;
            personaOneCard.current_x = Settings.WIDTH / 4F * Settings.scale;
            personaOneCard.current_y = (Settings.HEIGHT / 2F - 200F) * Settings.scale;
        }
        if (personaTwo.hovered && InputHelper.justClickedRight) {
            personaTwoCard = new BlankPersonaCard();
            personaTwoCard.drawScale = 1.0f;
            personaTwoCard.current_x = Settings.WIDTH / 1.35F * Settings.scale;
            personaTwoCard.current_y = (Settings.HEIGHT / 2F - 200F) * Settings.scale;
        }
        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0 && this.personaChange) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                c.unhover();
                if (changePersonaOne) {
                    personaOneCard = (AbstractPersonaCard) c;
                    personaOneCard.drawScale = 1.0f;
                    personaOneCard.current_x = Settings.WIDTH / 4F * Settings.scale;
                    personaOneCard.current_y = (Settings.HEIGHT / 2F - 200F) * Settings.scale;
                    personaChange = false;
                    changePersonaOne = false;
                }
                if (changePersonaTwo) {
                    personaTwoCard = (AbstractPersonaCard) c;
                    personaTwoCard.drawScale = 1.0f;
                    personaTwoCard.current_x = Settings.WIDTH / 1.35F * Settings.scale;
                    personaTwoCard.current_y = (Settings.HEIGHT / 2F - 200F) * Settings.scale;
                    personaChange = false;
                    changePersonaTwo = false;
                }
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }

    private void updateCardPreview(AbstractCard c) {
    }

    private void openPersonaList() {
        AbstractDungeon.previousScreen = ScreenStatePatch.PERSONA_FUSION_SCREEN;
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c instanceof AbstractPersonaCard && personaOneCard != c && personaTwoCard != c) {
                tmp.addToTop(c);
            }
        }
        this.personaChange = true;
        if (tmp.size() != 0) {
            AbstractDungeon.gridSelectScreen.open(tmp, 1, "Pick a Persona to fuse.", false);
        }
    }


    private void getResultPersona() {
        String resultantArcana;
        ArrayList<AbstractPersonaCard> lesserPersonaList = new ArrayList<>();
        int checkValue = 0;
        String concatenatedArcana = personaOneCard.arcana + personaTwoCard.arcana;
        resultantArcana = ElizabethModInitializer.personaFusionCombinations.get(concatenatedArcana);
        if (resultantArcana == null) {
            concatenatedArcana = personaTwoCard.arcana + personaOneCard.arcana;
            resultantArcana = ElizabethModInitializer.personaFusionCombinations.get(concatenatedArcana);
        }
        int resultantValue = personaOneCard.personaValue + personaTwoCard.personaValue;
        System.out.println(resultantValue);
        if (resultantArcana == null) {
            return;
        }
        switch(resultantArcana) {
            case "Fool":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfFoolPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Magician":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfMagicianPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Priestess":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfPriestessPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Empress":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfEmpressPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Emperor":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfMagicianPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Hierophant":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfHierophantPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Lovers":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfLoversPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Chariot":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfChariotPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Justice":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfJusticePersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Hermit":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfHermitPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Fortune":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfFortunePersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Strength":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfStrengthPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "HangedMan":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfHangedManPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Death":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfDeathPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Devil":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfDevilPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Tower":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfTowerPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Star":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfStarPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Moon":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfMoonPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Sun":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfSunPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Judgement":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfJudgementPersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }
            case "Universe":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfUniversePersona) {
                    System.out.println(c);
                    if (c.personaValue == resultantValue) {
                        personaResultCard = c;
                        personaResultCard.drawScale = 1.0f;
                        personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                        personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                        break;
                    } else {
                        if (c.personaValue < resultantValue) {
                            lesserPersonaList.add(c);
                        }
                    }
                }
                if (lesserPersonaList.size() != 0) {
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue > checkValue) {
                            checkValue = c.personaValue;
                        }
                    }
                    for (AbstractPersonaCard c : lesserPersonaList) {
                        if (c.personaValue == checkValue) {
                            personaResultCard = c;
                            personaResultCard.drawScale = 1.0f;
                            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
                            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
                            break;
                        }
                    }
                }

        }
    }
}
