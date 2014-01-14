package entity;

import boundary.Graphic;

/**
 * Class to make a GoToJail-field, which sends the player to Jail
 * 
 * @author DTU 02312 Gruppe 19, 2014
 * 
 */
public class GoToJail extends Field {

	/**
	 * Constructor that takes all inputs needed for the class.
	 * 
	 * @param name The name of this field.
	 * @param gameBoard A reference to the gameboard the field is created on.
	 */
	public GoToJail(String name, GameBoard gameBoard) {
		super(name, gameBoard);
	}

	/**
	 * Method to take care of everything that should happen, when a player lands
	 * on this field. Sends the player to Jail.
	 */
	public void landOnField(Player player) {
		Graphic.goToJailMessage(player.getName());
		player.goToJail();
	}
}
