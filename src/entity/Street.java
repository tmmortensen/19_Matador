package entity;

/**
 * Class to make a Territory-field.
 * 
 * @author DTU 02312 Gruppe 19
 * 
 */
public class Street extends Ownable {
	private int baseRent;
	private GameBoard gameBoard; 
	private int[] associatedFields;

	/**
	 * Constructor that takes all inputs needed for the class.
	 * 
	 * @param name
	 *            The name of this field.
	 * @param rent
	 *            The rent a player should pay if landing on this field.
	 * @param price
	 *            The price of this field.
	 */
	public Street(String name, int baseRent, int price, GameBoard gameBoard, int associatedField) {
		super(name, price);
		this.baseRent = baseRent;
		this.gameBoard = gameBoard;
		associatedFields = new int[1];
		associatedFields[0] = associatedField;
	}
	
	public Street(String name, int baseRent, int price, GameBoard gameBoard, int associatedField0, int associatedField1) {
		super(name, price);
		this.baseRent = baseRent;
		this.gameBoard = gameBoard;
		associatedFields = new int[2];
		associatedFields[0] = associatedField0;
		associatedFields[1] = associatedField1;
	}

	/**
	 * Method to calculate rent for this field.
	 * 
	 * @return The rent for this field.
	 */
	public int getRent() {
		int rent = baseRent;
		
		if(ownsAllAssociatedFields()) {
			rent = rent * 2;
		}
		
		return rent;
	}
	
	private boolean ownsAllAssociatedFields() {
		int i;
		
		for(i = 0; i < associatedFields.length; i++) {
			if (owner != gameBoard.getOwner(associatedFields[i])) {
				return false;
			}
		}
		
		return true;
	}
}