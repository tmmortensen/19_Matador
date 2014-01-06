package entity;

/**
 * Class to make a LaborCamp field.
 * 
 * @author DTU 02312 Gruppe 19
 * 
 */
public class Brewery extends Ownable {
	private final int[] BREWERY_FIELDS = { 13, 29 };

	private GameBoard gameBoard;
	private int baseRent;

	/**
	 * Constructor that takes all inputs needed for the class.
	 * 
	 * @param name
	 *            The name of the field.
	 * @param baseRent
	 *            The baseRent to multiply with dice and number of LaborCamps
	 *            owned
	 * @param gameBoard
	 *            The gameboard that this field is created in.
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
		return baseRent * gameBoard.getDieCupSum() * getBrewerysOwned();
	}

	private int getBrewerysOwned() {
		int i, numberOfBrewerysOwned = 0;

		for (i = 0; i < BREWERY_FIELDS.length; i++) {
			if (owner == gameBoard.getOwner(BREWERY_FIELDS[i])) {
				numberOfBrewerysOwned++;
			}
		}

		return numberOfBrewerysOwned;
	}

}