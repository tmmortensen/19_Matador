package tryYourLuck;

import entity.Player;

/**
 * Nedarvet fra Cards
 * 
 * @author Gruppe 16
 * 
 */
public class MoneyCards extends Cards {
	private int effect;

	/**
	 * Konstruktøren for de kort der skal behandle penge
	 * 
	 * @param cardText
	 *            Kortets tekst
	 * @param cardType
	 *            Kortets type
	 * @param effect
	 *            Antallet af penge man får eller mister
	 */
	public MoneyCards(String cardText, int effect) {
		super(cardText);
		this.effect = effect;
	}

	@Override
	/**
	 * Overskrevet fra Cards
	 */
	public void calcEffect(Player player) {
		player.addToAccount(effect);
	}

}