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
	 * Method to take care of everything that should happen, when a player lands
	 * on this field. Has different implementations for different types of
	 * fields.
	 * 
	 * @param player
	 *            The player that landed on the field.
	 */
	public abstract void landOnField(Player player);
	
	/**
	 * Method to get the name of the field.
	 * 
	 * @return The name of the field.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Method to get content of class as a string.
	 */
	public String toString() {
		return name;
	}

	
	protected void performStdActions(Actions action, Player player) {
		switch(action) {
			case SELL_FIELD:
				gameBoard.sellField(player);
				break;
			case PLEDGE_FIELD:
				gameBoard.pledgeField(player);
				break;
			case UNPLEDGE_FIELD:
				gameBoard.unpledgeField(player);
				break;
			case SELL_HOUSE:
				gameBoard.sellHouse(player);
				break;
			case BUY_HOUSE:
				gameBoard.buyHouse(player);
				break;
			default:
				break;
		}
	}
}