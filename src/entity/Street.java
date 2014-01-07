package entity;

/**
 * Class to make a Territory-field.
 * 
 * @author DTU 02312 Gruppe 19
 * 
 */
public class Street extends Ownable {
	private int constructPrice;
	private int[] associatedFields, rents;
	private GameBoard gameBoard;

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
	public Street(String name, int price, int constructPrice, int[] associatedFields, int[] rents, GameBoard gameBoard) {
		super(name, price);
		this.constructPrice = constructPrice;
		this.associatedFields = associatedFields;
		this.rents = rents;
		this.gameBoard = gameBoard;
	}

	/**
	 * Method to calculate rent for this field.
	 * 
	 * @return The rent for this field.
	 */
	public int getRent() {
		int rent = rents[0];
		
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