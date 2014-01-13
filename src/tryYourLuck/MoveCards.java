package tryYourLuck;

import entity.Player;

/**
 * Nedarvet fra Cards
 * 
 * @author Gruppe 16
 * 
 */
public class MoveCards extends Cards {
	private int position;
	private MoveCardTypes moveCardType;

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
	public MoveCards(String cardText, MoveCardTypes moveCardType, int position) {
		super(cardText);
		this.position = position;
		this.moveCardType = moveCardType;
	}
	
	public enum MoveCardTypes {
		MOVEFORWARD, MOVETO, GOTOJAIL;
	}

	@Override
	/**
	 * Overskrevet fra Cards, til at beregne hvad der skal ske hvis et bestemt kort bliver trukket
	 */
	public void calcEffect(Player player) {
		switch (moveCardType) {
			case MOVETO:
				player.setLocation(position);
				break;
			case MOVEFORWARD:
				player.moveFieldsForward(position);
				break;
			case GOTOJAIL:
				player.goToJail();
				break;
			default:
				break;
		}
	}
}
