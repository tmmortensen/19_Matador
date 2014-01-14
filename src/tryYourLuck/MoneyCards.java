package tryYourLuck;

import entity.Player;

/**
 * Class to make a moneycard. Extends Cards
 * 
 * @author DTU 02312 Gruppe 19, 2014
 * 
 */
public class MoneyCards extends Cards {
	private int effect;

	/**
	 * Constructor that takes all inputs needed for the class.
	 * 
	 * @param cardText The text of the card
	 * @param effect The score that the player will get/lose
	 */
	public MoneyCards(String cardText, int effect) {
		super(cardText);
		this.effect = effect;
	}

	/**
	 * Calculates the effect of this card
	 */
	public void calcEffect(Player player) {
		player.addToAccount(effect);
	}

}