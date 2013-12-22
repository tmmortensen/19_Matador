package entity;

/**
 * Class to make a LaborCamp field.
 *
 * @author DTU 02312 Gruppe 19
 *
 */
public class Brewery extends Ownable {
	private final int[] LABOR_CAMP_FIELDS = { 14, 15 };

	private GameBoard gameBoard;
	private int baseRent;

	/**
	 * Constructor that takes all inputs needed for the class.
	 *
	 * @param name The name of the field.
	 * @param baseRent The baseRent to multiply with dice and number of LaborCamps owned
	 * @param gameBoard The gameboard that this field is created in.
	 */
	public Brewery(String name, int baseRent, int price, GameBoard gameBoard) {
		super(name, price);
		this.gameBoard = gameBoard;
		this.baseRent = baseRent;
	}

	/**
	 * Method to calculate rent for this field.
	 *
	 * @return The rent for this field.
	 */
	public int getRent() {
		gameBoard.shakeDieCup();
		return baseRent * gameBoard.getDieCupSum() * getLaborCampsOwned();
	}

	private int getLaborCampsOwned() {
		int i, numberOfLaborCampsOwned = 0;

		for (i = 0; i < LABOR_CAMP_FIELDS.length; i++) {
			if (owner == gameBoard.getOwner(LABOR_CAMP_FIELDS[i])) {
				numberOfLaborCampsOwned++;
			}
		}

		return numberOfLaborCampsOwned;
	}
}