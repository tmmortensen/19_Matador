package entity;

/**
 * Class to create a field. This class can be used to contain the score of a field and a value for extra turn.
 *
 * @author DTU 02312 Gruppe 19
 *
 */
public abstract class Field {
	protected String name;

	/**
	 * Constructor to set field name.
	 *
	 * @param name Name of field.
	 */
	public Field(String name) {
		this.name = name;
	}

	/**
	 * Method to get the name of the field.
	 *
	 * @return The name of the field.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to take care of everything that should happen, when a player lands on this field.
	 * Has different implementations for different types of fields.
	 *
	 * @param player The player that landed on the field.
	 */
	public abstract void landOnField(Player player);

	/**
	 * Method to get content of class as a string.
	 */
	public String toString() {
		return name;
	}
}