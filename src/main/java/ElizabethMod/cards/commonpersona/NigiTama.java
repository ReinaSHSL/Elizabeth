package ElizabethMod.cards.commonpersona;

import ElizabethMod.actions.MoveBasicPersonaToTopOfDeckAction;
import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.security.UnresolvedPermission;


public class NigiTama extends AbstractPersonaCard {
    public static final String ID = "Elizabeth:NigiTama";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "ElizabethImgs/cards/NigiTama.png";
    private static final int COST = 1;
    private static final CardRarity rarity = CardRarity.BASIC;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final int DAMAGE_AMT = 7;
    private static final int UPGRADE_DMG = 2;

    public NigiTama() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.VELVET_BLUE,
                        rarity, target);
        this.arcana = "Temperance";
        this.personaValue = 1;
        this.damage = this.baseDamage = DAMAGE_AMT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        super.bonus();
        if (this.doBonus) {
            AbstractDungeon.actionManager.addToBottom(new MoveBasicPersonaToTopOfDeckAction());
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new NigiTama();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DMG);
        }
    }
}
