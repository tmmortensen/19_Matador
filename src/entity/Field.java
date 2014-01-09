package entity;

import boundary.Graphic.Actions;

/**
 * Class to create a field. This class can be used to contain the score of a
 * field and a value for extra turn.
 * 
 * @author DTU 02312 Gruppe 19
 * 
 */
public abstract class Field {
	protected String name;
	protected GameBoard gameBoard;

	/**
	 * Constructor to set field name.
	 * 
	 * @param name
	 *            Name of field.
	 */
	public Field(String name, GameBoard gameBoard) {
		this.name = name;
		this.gameBoard = gameBoard;
	}

	/**
	 * Method to get the name of the field.
	 * 
	 * @return The name of the field.
	 */
	public String getName() {
		return name;
	}

	protected void performStdActions(Actions action, Player player) {
		switch(action) {
			case SELL_FIELD:
				sellField(player);
				break;
			case MORTAGE_FIELD:
				mortageField(player);
				break;
			case SELL_HOUSE:
				sellHouse(player);
				break;
			default:
				break;
		}
	}
	
	private void sellField(Player player) {
		//TODO: Sell field
		gameBoard.sellField(player);
	}
	
	private void mortageField(Player player) {
		//TODO: Mortage field
	}
	
	private void sellHouse(Player player) {
		//TODO: Sell house 
	}
	
	/**
	 * Method to take care of everything that should happen, when a player lands
	 * on this field. Has different implementations for different types of
	 * fields.
	 * 
	 * @param player
	 *            The player that landed on the field.
	 */
	public abstract void landOnField(Player player);

	/**
	 * Method to get content of class as a string.
	 */
	public String toString() {
		return name;
	}

}