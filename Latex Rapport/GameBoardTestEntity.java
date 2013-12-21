package TestTools;

import Entity.Field;

/**
 * Class to create a "testing" game board. This will work exactly like the "real" gameboard,
 * except for the field values which are customized to test the limits of the methods in account and game-classes.
 *
 * @author DTU 02312 Gruppe 19
 *
 */
public class GameBoardTestEntity {
	Field[] fields;

	/**
	 * Constructor that makes an array of fields and sets it to some good testing values.
	 */
	public GameBoardTestEntity() {
		fields = new Field[13];

		// Create the fields
		fields[2] = new Field(-3001);
		fields[3] = new Field(-3000);
		fields[4] = new Field(-2999);
		fields[5] = new Field(-4000);
		fields[6] = new Field(-5500);
		fields[7] = new Field(-7000);
		fields[8] = new Field(-700000);
		fields[9] = new Field(-1000000);
		fields[10] = new Field(-8000, true);
		fields[11] = new Field(-9000);
		fields[12] = new Field(-6500);
	}

	/**
	 * Takes the number of a field and gives the corresponding field-object.
	 * 
	 * @param fieldNumber The number of the field to get.
	 * @return The field object corresponding to the number given.
	 */
	public Field getField(int fieldNumber) {
		return fields[fieldNumber];
	}

	/**
	 * A method to generate a nice string containing the value of all the fields.
	 * 
	 * @return All the field values as a string.
	 */
	public String toString() {
		String output = "";
		int i;

		for (i = 0; i < fields.length; i++) {
			if (fields[i] != null) {
				output = output + fields[i] + "\n";
			}
		}

		return output;
	}
}
