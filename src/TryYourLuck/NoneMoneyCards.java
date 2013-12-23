package TryYourLuck;

import boundaryToMatador.GUI;
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
	public void calcEffekt(Player players) {

		switch (cardType) {
		case MOVETO:
			players.setLocation(position);
			break;
		case CHANGEPOSITION:
			int changedPosition;
			changedPosition = players.getLocation() + position;
			if (changedPosition == 41)
				changedPosition = 1;
			if (changedPosition < 0)
				changedPosition = 40 + changedPosition;
			players.setLocation(changedPosition);
			break;
		case GOTOJAIL:
			players.setLocation(11);
			GUI.setCar(players.getLocation(), players.getName());
			// players[currentPlayer].jailTransfer();
			break;
		default:
			// gør ingenting, men sikrer at spillet ikke bryder ned hvis der
			// ikke bliver trukket et kort af en eller anden grund
			break;
		}
	}
}
