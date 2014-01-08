package entity;

import boundary.Graphic.Actions;

/**
 * Class to make a Shipping-field.
 * 
 * @author DTU 02312 Gruppe 19
 * 
 */
public class Shipping extends Ownable {
	private final int[] SHIPPING_FIELDS = { 6, 16, 26, 36 };

	private final int RENT_1 = 500;
	private final int RENT_2 = 1000;
	private final int RENT_3 = 2000;
	private final int RENT_4 = 4000;

	/**
	 * Constructor that takes all inputs needed for the class.
	 * 
	 * @param name
	 *            The name of the field.
	 * @param price
	 *            The price of the field.
	 * @param gameBoard
	 *            The gameboard that this field is created in.
	 */
	public Shipping(String name, int price, GameBoard gameBoard) {
		super(name, price, gameBoard);
	}

	/**
	 * Method to calculate rent for this field.
	 * 
	 * @return The rent for this field.
	 */
	public int getRent() {
		int numberOfShippingsOwned = getShippingsOwned();

		switch (numberOfShippingsOwned) {
		case 1:
			return RENT_1;
		case 2:
			return RENT_2;
		case 3:
			return RENT_3;
		case 4:
			return RENT_4;
		default:
			return 0;
		}
	}

	protected boolean isBuildable() {
		return false;
	}
	
	protected void performAction(Actions action, Player player) {
		performStdActions(action, player);
		
		if(action == Actions.BUY_FIELD) {
			buyField(player);
		}
	}
	
	private int getShippingsOwned() {
		int i, numberOfShippingsOwned = 0;

		for (i = 0; i < SHIPPING_FIELDS.length; i++) {
			if (owner == gameBoard.getOwner(SHIPPING_FIELDS[i])) {
				numberOfShippingsOwned++;
			}
		}

		return numberOfShippingsOwned;
	}
}