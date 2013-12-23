package TryYourLuck;

import entity.Player;

public abstract class Cards {

	protected Card cardType;
	protected String cardText;

	/**
	 * Konstruktøren
	 * 
	 * @param cardType
	 *            Hvilken type kort
	 * @param cardText
	 *            Hvad der skal stå på kortet
	 */
	public Cards(Card cardType, String cardText) {
		this.cardType = cardType;
		this.cardText = cardText;
	}

	public enum Card {
		WITHDRAWCARD, DEPOSITCARD, CHANGEPOSITION, MOVETO, GOTOJAIL;
	}

	/**
	 * Beregner kortets efekt
	 * 
	 * @param player
	 *            Spillerarrayet
	 */
	public abstract void calcEffekt(Player player);

	/**
	 * Henter korttypen
	 * 
	 * @return Returnerer korttypen
	 */
	public Card getCardType() {
		return cardType;
	}

	public String getCardText() {
		return "" + cardText;
	}

}
