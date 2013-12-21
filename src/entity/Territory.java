package entity;

/**
 * Class to make a Territory-field.
 *
 * @author DTU 02312 Gruppe 19
 *
 */
public class Territory extends Ownable {
	private int rent;

	/**
	 * Constructor that takes all inputs needed for the class.
	 *
	 * @param name The name of this field.
	 * @param rent The rent a player should pay if landing on this field.
	 * @param price The price of this field.
	 */
	public Territory(String name, int rent, int price) {
		super(name, price);
		this.rent = rent;
	}

	/**
	 * Method to calculate rent for this field.
	 *
	 * @return The rent for this field.
	 */
	public int getRent() {
		return rent;
	}
}