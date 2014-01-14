package tryYourLuck;

import entity.Player;

/**
 * Class to make a movecard. Extends Cards
 * 
 * @author DTU 02312 Gruppe 19, 2014
 * 
 */
public class MoveCards extends Cards {
	private int position;
	private MoveCardTypes moveCardType;

	/**
	 * Constructor that takes all inputs needed for the class.
	 * 
	 * @param cardText The text of the card
	 * @param moveCardType The type of the card, as an enum type
	 * @param position How many fields the player should move, or where to move to
	 */
	public MoveCards(String cardText, MoveCardTypes moveCardType, int position) {
		super(cardText);
		this.position = position;
		this.moveCardType = moveCardType;
	}
	
	public enum MoveCardTypes {
		MOVEFORWARD, MOVETO, GOTOJAIL;
	}

	/**
	 * Calculates the effect of this card, depending on the type of the card
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
