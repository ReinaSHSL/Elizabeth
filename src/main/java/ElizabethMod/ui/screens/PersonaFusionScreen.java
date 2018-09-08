package ElizabethMod.ui.screens;

import ElizabethMod.ElizabethModInitializer;
import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.cards.screencards.BlankPersonaCard;
import ElizabethMod.effects.ExhaustEffectTop;
import ElizabethMod.patches.ScreenStatePatch;
import ElizabethMod.tools.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;

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
    public static boolean personaChange = false;
    private boolean changePersonaOne = false;
    private boolean changePersonaTwo = false;
    private static Hitbox fuseButtonHb;
    private static Texture fuseButton;
    private static Hitbox compendiumButtonHb;
    private static Texture compendiumButton;
    private boolean shouldRender = true;
    private float duration;
    private boolean updateHitboxes = true;

    public PersonaFusionScreen() {
    }

    public void open(boolean resetCards) {
        updateHitboxes = true;
        if (resetCards) {
            personaOneCard = (AbstractPersonaCard) new BlankPersonaCard().makeStatEquivalentCopy();
            personaTwoCard = (AbstractPersonaCard) new BlankPersonaCard().makeStatEquivalentCopy();
            personaResultCard = (AbstractPersonaCard) new BlankPersonaCard().makeStatEquivalentCopy();
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
        fuseButton = TextureLoader.getTexture("ElizabethImgs/ui/buttons/fuseButton.png");
        fuseButtonHb = new Hitbox((Settings.WIDTH / 2F - fuseButton.getWidth() / 2F)  * Settings.scale, (Settings.HEIGHT / 2F - 300F) * Settings.scale,
                fuseButton.getWidth(), fuseButton.getHeight());
        compendiumButton = TextureLoader.getTexture("ElizabethImgs/ui/buttons/compendiumButton.png");
        compendiumButtonHb = new Hitbox((Settings.WIDTH - compendiumButton.getWidth() / 2F) * Settings.scale, 0,
                (compendiumButton.getWidth() / 1.5F + 200) * Settings.scale, compendiumButton.getHeight() / 1.5F * Settings.scale);
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
        sb.draw(fuseButton, (Settings.WIDTH / 2F - fuseButton.getWidth() / 2F)  * Settings.scale, (Settings.HEIGHT / 2F - 300F) * Settings.scale);
        sb.draw(compendiumButton, Settings.WIDTH - compendiumButton.getWidth() / 1.5F * Settings.scale, 0,
                compendiumButton.getWidth() / 1.5F * Settings.scale, compendiumButton.getHeight() / 1.5F * Settings.scale);
        renderObjects(sb);
        justClicked = false;
    }

    private void renderObjects(SpriteBatch sb) {
        if (shouldRender) {
            personaOneCard.render(sb);
            personaOne.render(sb);
            personaTwoCard.render(sb);
            personaTwo.render(sb);
            personaResultCard.render(sb);
            personaResult.render(sb);
            fuseButtonHb.render(sb);
            compendiumButtonHb.render(sb);
        }
    }

    public void update() {
        ElizabethModInitializer.compendiumScreen.update();
        if (AbstractDungeon.overlayMenu.cancelButton.isHidden) {
            AbstractDungeon.overlayMenu.cancelButton.show("Return");
        }
        if (updateHitboxes) {
            personaOne.update();
            personaTwo.update();
            personaResult.update();
            fuseButtonHb.update();
            compendiumButtonHb.update();
        }
        getResultPersona();
        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F) {
            shouldRender = true; }
        if (InputHelper.justClickedLeft) {
            justClicked = true;
        }
        if (fuseButtonHb.hovered && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            TipHelper.renderGenericTip((Settings.WIDTH / 2F - fuseButton.getWidth() / 2F)  * Settings.scale, (Settings.HEIGHT / 2F - 125F),
                    "Notice!", "You cannot fuse during combat.");
        }
        if (fuseButtonHb.hovered && InputHelper.justClickedLeft && AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) {
            shouldRender = false;
            duration = 0.9F;
            AbstractDungeon.topLevelEffectsQueue.add(new ExhaustEffectTop(personaOneCard));
            AbstractDungeon.player.masterDeck.removeCard(personaOneCard);
            AbstractDungeon.topLevelEffectsQueue.add(new ExhaustEffectTop(personaTwoCard));
            AbstractDungeon.player.masterDeck.removeCard(personaTwoCard);
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(personaResultCard.makeStatEquivalentCopy(),
                    Settings.WIDTH / 2F * Settings.scale, Settings.HEIGHT / 2F * Settings.scale, false));
            personaOneCard = (AbstractPersonaCard) new BlankPersonaCard().makeStatEquivalentCopy();
            personaOneCard.drawScale = 1.0f;
            personaOneCard.current_x = Settings.WIDTH / 4F * Settings.scale;
            personaOneCard.current_y = (Settings.HEIGHT / 2F - 200F) * Settings.scale;
            personaTwoCard = (AbstractPersonaCard) new BlankPersonaCard().makeStatEquivalentCopy();
            personaTwoCard.drawScale = 1.0f;
            personaTwoCard.current_x = Settings.WIDTH / 1.35F * Settings.scale;
            personaTwoCard.current_y = (Settings.HEIGHT / 2F - 200F) * Settings.scale;
            personaResultCard = (AbstractPersonaCard) new BlankPersonaCard().makeStatEquivalentCopy();
            personaResultCard.drawScale = 1.0f;
            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
        }
        if (compendiumButtonHb.hovered && InputHelper.justClickedLeft) {
            updateHitboxes = false;
            ElizabethModInitializer.compendiumScreen.open();
        }
        SpriteBatch sb = null;
        if (personaOne.hovered) {
            updateCardPreview(personaOneCard, sb);
        }
        if (personaTwo.hovered) {
            updateCardPreview(personaTwoCard, sb);
        }
        if (personaResult.hovered) {
            updateCardPreview(personaResultCard, sb);
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
            personaOneCard = (AbstractPersonaCard) new BlankPersonaCard().makeStatEquivalentCopy();
            personaOneCard.drawScale = 1.0f;
            personaOneCard.current_x = Settings.WIDTH / 4F * Settings.scale;
            personaOneCard.current_y = (Settings.HEIGHT / 2F - 200F) * Settings.scale;
            personaResultCard = (AbstractPersonaCard) new BlankPersonaCard().makeStatEquivalentCopy();
            personaResultCard.drawScale = 1.0f;
            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
        }
        if (personaTwo.hovered && InputHelper.justClickedRight) {
            personaTwoCard = (AbstractPersonaCard) new BlankPersonaCard().makeStatEquivalentCopy();
            personaTwoCard.drawScale = 1.0f;
            personaTwoCard.current_x = Settings.WIDTH / 1.35F * Settings.scale;
            personaTwoCard.current_y = (Settings.HEIGHT / 2F - 200F) * Settings.scale;
            personaResultCard = (AbstractPersonaCard) new BlankPersonaCard().makeStatEquivalentCopy();
            personaResultCard.drawScale = 1.0f;
            personaResultCard.current_x = Settings.WIDTH / 2F * Settings.scale;
            personaResultCard.current_y = (Settings.HEIGHT / 2F + 100F) * Settings.scale;
        }
        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0 && personaChange && !CompendiumScreen.compendiumList) {
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

    private void updateCardPreview(AbstractCard c, SpriteBatch sb) {
        TipHelper.renderTipForCard(c, sb, c.keywords);
    }

    private void openPersonaList() {
        AbstractDungeon.previousScreen = ScreenStatePatch.PERSONA_FUSION_SCREEN;
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c instanceof AbstractPersonaCard && personaOneCard != c && personaTwoCard != c) {
                tmp.addToTop(c);
            }
        }
        CompendiumScreen.compendiumList = false;
        if (tmp.size() != 0) {
            personaChange = true;
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
        //System.out.println(resultantValue);
        if (resultantArcana == null) {
            return;
        }
        switch(resultantArcana) {
            case "Fool":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfFoolPersona) {
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfEmperorPersona) {
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                    //System.out.println(c);
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
            case "Temperance":
                for (AbstractPersonaCard c : ElizabethModInitializer.listOfTemperancePersona) {
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                    //System.out.println(c);
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
                    //System.out.println(c);
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
            case "null":
                break;

        }
    }
}
