package TryYourLuck;

import entity.Player;

/**
 * Nedarvet fra Cards
 * 
 * @author Gruppe 16
 * 
 */
public class NoneMoneyCards extends Cards {

	protected int typeOfNoneMoneyCard;
	protected int position;

	/**
	 * Konstruktør for kort der ikke behandler penge
	 * 
	 * @param cardText
	 *            Hvad der skal stå på kortet
	 * @param cardType
	 *            Typen af kort
	 * @param position
	 *            Hvor spilleren skal flyttes hen hvis de får kortet
	 */
	public NoneMoneyCards(String cardText, Card cardType, int position) {
		super(cardType, cardText);
		this.position = position;
	}

	@Override
	/**
	 * Overskrevet fra Cards, til at beregne hvad der skal ske hvis et bestemt kort bliver trukket
	 */
	public void calcEffect(Player player) {

		switch (cardType) {
		case MOVETO:
			player.setLocation(position);
			break;
		case CHANGEPOSITION:
			player.moveFieldsForward(position);
			break;
		case GOTOJAIL:
			player.goToJail();
			break;
		default:
			// gør ingenting, men sikrer at spillet ikke bryder ned hvis der
			// ikke bliver trukket et kort af en eller anden grund
			break;
		}
	}
}
