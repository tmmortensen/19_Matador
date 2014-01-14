package entity;

import boundary.Graphic;
import boundary.Graphic.Actions;

/**
 * Class to make a Refuge-field.
 * 
 * @author DTU 02312 Gruppe 19
 * 
 */
public class Refuge extends Field {
	int bonus;

	/**
	 * Constructor that takes all inputs needed for the class.
	 * 
	 * @param name
	 *            The name of this field.
	 * @param bonus
	 *            How much the field should give as bonus.
	 */
	public Refuge(String name, int bonus, GameBoard gameBoard) {
		super(name, gameBoard);
		this.bonus = bonus;
	}

	/**
	 * Method to take care of everything that should happen, when a player lands
	 * on this field. Adds the bonus to the player given.
	 */
	public void landOnField(Player player) {
		if(!player.isInJail()) {
			player.addToAccount(bonus);
			Actions action = null;
			while(action != Actions.END) {
				action = Graphic.showMenu(player.getName(), this.name, 0, 0, false, false, null);
				performStdActions(action, player);
			}
		}
	}
}