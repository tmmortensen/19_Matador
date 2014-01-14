package tryYourLuck;

import entity.Player;

/**
 * Abstract class to make a card.
 * 
 * @author DTU 02312 Gruppe 19, 2014
 * 
 */
public abstract class Cards {
	private String cardText;

	/**
	 * Constructor that takes all inputs needed for the class.
	 * 
	 * @param cardText The text-message on the card
	 */
	public Cards(String cardText) {
		this.cardText = cardText;
	}

	/**
	 * Calculates the effect of the card
	 * 
	 * @param player The player who drew the card
	 */
	public abstract void calcEffect(Player player);
 
	/**
	 * Get the text-massage from the card
	 * 
	 * @return The message on the card
	 */
	public String getCardText() {
		return cardText;
	}
}
