package tryYourLuck_FIX;

import entity.Player;

public abstract class Cards {
	private String cardText;

	/**
	 * Konstruktøren
	 * 
	 * @param cardType
	 *            Hvilken type kort
	 * @param cardText
	 *            Hvad der skal stå på kortet
	 */
	public Cards(String cardText) {
		this.cardText = cardText;
	}

	/**
	 * Beregner kortets efekt
	 * 
	 * @param player
	 *            Spillerarrayet
	 */
	public abstract void calcEffect(Player player);
 
	public String getCardText() {
		return cardText;
	}
}
