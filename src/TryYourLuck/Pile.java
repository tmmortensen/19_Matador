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
		pile[0] = new MoneyCards(
				"Try your luck\nHappy birthday! You are getting 2000 kr.",
				Card.DEPOSITCARD, 2000);
		pile[1] = new NoneMoneyCards("Try your luck\nMove to start",
				Card.MOVETO, 1);
		pile[2] = new MoneyCards(
				"Try your luck\nYou are driving too fast, you are fined for 1500 kr.",
				Card.WITHDRAWCARD, 1500);
		pile[3] = new NoneMoneyCards("Try your luck\nMove to Rådhuspladsen",
				Card.MOVETO, 40);
		pile[4] = new MoneyCards(
				"Try your luck\nYou are getting money back from tax. You recieve 3000 kr.",
				Card.DEPOSITCARD, 3000);
		pile[5] = new NoneMoneyCards(
				"Try your luck\nMove 4 fieldnumbers forward",
				Card.CHANGEPOSITION, 4);
		pile[6] = new NoneMoneyCards("Try your luck\nGo to Jail!",
				Card.GOTOJAIL, 0);
		pile[7] = new NoneMoneyCards(
				"Try your luck\nMove 4 fieldnumbers backward",
				Card.CHANGEPOSITION, -4);
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
	public String getCardType(int cardNumber) {
		if (cardNumber == -1)
			return null;
		else
			return shuffledPile[cardNumber].getCardType().toString();
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
