package TryYourLuck;

import entity.Player;

/**
 * Nedarvet fra Cards
 * 
 * @author Gruppe 16
 * 
 */
public class MoneyCards extends Cards {

	protected int effect;
	protected int typeOfMoneyCard;

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
	public MoneyCards(String cardText, Card cardType, int effect) {
		super(cardType, cardText);
		this.effect = effect;
	}

	@Override
	/**
	 * Overskrevet fra Cards
	 */
	public void calcEffect(Player players) {

		if (cardType == Card.WITHDRAWCARD) {
			players.addToAccount(effect);
		}
	}

}