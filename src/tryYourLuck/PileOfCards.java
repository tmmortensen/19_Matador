package tryYourLuck;

import tryYourLuck.MoveCards.MoveCardTypes;
import entity.Player;

/**
 * Bunken af kort
 * 
 * @author Gruppe 16
 * 
 */
public class PileOfCards {
	private Cards[] pile;
	private Cards[] shuffledPile;
	private int cardNumber;

	/**
	 * Konstruktør som opretter kortene i et array
	 */
	public PileOfCards() {
		pile = new Cards[8];
		shuffledPile = new Cards[8];
		cardNumber = -1;
				
		createCards();
		shufflePile();
	}

	/**
	 * Tager det næste kort frem i bunken
	 */
	public void nextCard() {
		cardNumber++;
		if (cardNumber > pile.length - 1) {
			cardNumber = 0;
		}
	}
	
	public boolean isMoneyCard() {
		return shuffledPile[cardNumber] instanceof MoneyCards;
	}

	/**
	 * Beregner effekten af kortet
	 * 
	 * @param player
	 *            Spillerarrayet
	 */
	public void effect(Player player) {
		shuffledPile[cardNumber].calcEffect(player);
	}

	/**
	 * Sender kortets tekst vdere
	 * 
	 * @return Returnerer kortets tekst
	 */
	public String getCardText() {
		return shuffledPile[cardNumber].getCardText();
	}

	
	private void createCards() {
		pile[0] = new MoneyCards("Tillykke med fødselsdagen! Du modtager Kr. 2000,-", +2000);
		pile[1] = new MoveCards("Ryk til Start", MoveCardTypes.MOVETO, 1);
		pile[2] = new MoneyCards("Du kørte for hurtigt, modtag en bøde på Kr. 1500,-", -1500);
		pile[3] = new MoveCards("Ryk til Rådhuspladsen", MoveCardTypes.MOVETO, 40);
		pile[4] = new MoneyCards("Du har fået penge tilbage i skat. Modtag Kr. 3000,-", +3000);
		pile[5] = new MoveCards("Flyt 4 felter frem", MoveCardTypes.CHANGEPOSITION, +4);
		pile[6] = new MoveCards("Gå i Fængsel!", MoveCardTypes.GOTOJAIL, 0);
		pile[7] = new MoveCards("Flyt 4 felter baglæns", MoveCardTypes.CHANGEPOSITION, -4);
	}
	
	/**
	 * Blander bunken
	 */
	private void shufflePile() {
		for (int i = 0; i < pile.length; i++) {
			int randNum = (int) (Math.random() * pile.length);
			while (shuffledPile[randNum] != null) {
				randNum = (int) (Math.random() * pile.length);
			}
			shuffledPile[randNum] = pile[i];
		}
	}

}
