package entity;

/**
 * Class to make a Shipping-field.
 * 
 * @author DTU 02312 Gruppe 19
 * 
 */
public class Shipping extends Ownable {
	private final int[] SHIPPING_FIELDS = { 6, 16, 26, 36 };
	private final int[] RENTS = { 500, 1000, 2000, 4000 };

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

	public boolean isBuildable() {
		return false;
	}
	
	
	/**
	 * Method to calculate rent for this field.
	 * 
	 * @return The rent for this field.
	 */
	protected int getRent() {
		int numberOfShippingsOwned = getShippingsOwned();

		return RENTS[numberOfShippingsOwned-1];
	}
	
	
	private int getShippingsOwned() {
		int i, numberOfShippingsOwned = 0;

		for (i = 0; i < SHIPPING_FIELDS.length; i++) {
			if (owner == gameBoard.getOwner(SHIPPING_FIELDS[i])) {
				Ownable fieldToTest = (Ownable)gameBoard.getField(SHIPPING_FIELDS[i]);
				
				if(!fieldToTest.isPledged) {
					numberOfShippingsOwned++;
				}
			}
		}

		return numberOfShippingsOwned;
	}
}