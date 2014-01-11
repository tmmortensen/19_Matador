package TryYourLuck;

import TryYourLuck.Cards.Card;
import entity.Player;

/**
 * Bunken af kort
 * 
 * @author Gruppe 16
 * 
 */
public class Pile {
	private Cards[] pile = new Cards[8];
	private Cards[] shuffledPile = new Cards[8];
	private int cardNumber = -1;
	private int oldCardNumber = -1;

	/**
	 * Konstruktør som opretter kortene i et array
	 */
	public Pile() {
		pile[0] = new MoneyCards("Tillykke med fødselsdagen! Du modtager Kr. 2000,-", Card.DEPOSITCARD, +2000);
		pile[1] = new NoneMoneyCards("Ryk til Start", Card.MOVETO, 1);
		pile[2] = new MoneyCards("Du kørte for hurtigt, modtag en bøde på Kr. 1500,-", Card.WITHDRAWCARD, -1500);
		pile[3] = new NoneMoneyCards("Ryk til Rådhuspladsen", Card.MOVETO, 40);
		pile[4] = new MoneyCards("Du har fået penge tilbage i skat. Modtag Kr. 3000,-", Card.DEPOSITCARD, +3000);
		pile[5] = new NoneMoneyCards("Flyt 4 felter frem", Card.CHANGEPOSITION, +4);
		pile[6] = new NoneMoneyCards("Gå i Fængsel!", Card.GOTOJAIL, 0);
		pile[7] = new NoneMoneyCards("Flyt 4 felter baglæns", Card.CHANGEPOSITION, -4);
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

	/**
	 * Henter typen af kort
	 * 
	 * @param cardNumber
	 *            Kortets nummer
	 * @return Returnerer kortets nummer som en String
	 */
	public Card getCardType() {
		if (cardNumber == -1)
			return null;
		else
			return shuffledPile[cardNumber].getCardType();
	}

	/**
	 * Henter kortets nummer
	 * 
	 * @param check
	 *            Finder ud af om man bare skal returnerer cardNumber eller om
	 *            man først skal sætte oldCardNumber til cardNumber
	 * @return korttallet
	 */
	public int getCardNumber(boolean check) {
		if (oldCardNumber == cardNumber) {
			return -1;
		} else {
			if (!check)
				oldCardNumber = cardNumber;
			return cardNumber;
		}
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
	 * Sender kortets tekst vedere
	 * 
	 * @return Returnerer kortets tekst
	 */
	public String ShowCardText() {
		return shuffledPile[cardNumber].getCardText();
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
