package ElizabethMod.cards.commonpersona;

import ElizabethMod.actions.StunMonsterAction;
import ElizabethMod.cards.AbstractPersonaCard;
import ElizabethMod.enums.AbstractCardEnum;
import ElizabethMod.powers.FrozenPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class JackFrost extends AbstractPersonaCard {
    public static final String ID = "Elizabeth:JackFrost";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "ElizabethImgs/cards/JackFrost.png";
    private static final int COST = 1;
    private static final CardRarity rarity = CardRarity.BASIC;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final int DAMAGE_AMT = 5;
    private static final int DAMAGE_UPGRADE = 2;

    public JackFrost() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.VELVET_BLUE,
                rarity, target);
        this.arcana = "Magician";
        this.personaValue = 1;
        this.damage = this.baseDamage = DAMAGE_AMT;
        this.goldValue = 80;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        super.bonus();
        if (this.doBonus) {
            this.cost = 3;
            AbstractDungeon.actionManager.addToBottom(new StunMonsterAction(m, p, FrozenPower.POWER_ID));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new JackFrost();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(DAMAGE_UPGRADE);
        }
    }
}
