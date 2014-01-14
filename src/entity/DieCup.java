package entity;

import boundary.Graphic;

/**
 * Class to create a diecup. This class will take in 2 dice.
 * 
 * @author DTU 02312 Gruppe 19, 2014
 * 
 */
public class DieCup {
	private Die die1, die2;

	/**
	 * Constructor that set up two new dice.
	 */
	public DieCup() {
		die1 = new Die();
		die2 = new Die();
	}

	/**
	 * Method to shake the diecup.
	 */
	public void shakeDieCup() {
		die1.roll();
		die2.roll();
		Graphic.setDice(die1.getValue(), die2.getValue());
	}

	/**
	 * Adds value from die1 and die2.
	 * 
	 * @return The sum of die1 and die2.
	 */
	public int getSum() {
		return die1.getValue() + die2.getValue();
	}

	/**
	 * Get faceValue from die1
	 * 
	 * @return The current value of die1.
	 */
	public int getValueDie1() {
		return die1.getValue();
	}

	/**
	 * Get faceValue from die2
	 * 
	 * @return The current value of die2.
	 */
	public int getValueDie2() {
		return die2.getValue();
	}

	/**
	 * Checks if the values of the dice are equal.
	 * 
	 * @return True if facevalues of die1 and die2 are the same, otherwise
	 *         False.
	 */
	public boolean getIdentical() {
		if (die1.getValue() == die2.getValue()) {
			return true;
		}
		return false;
	}

	/**
	 * Method that makes a text with the most important values in the class, and
	 * some description.
	 * 
	 * @return A coherent string with values of Die1 and Die2
	 */
	public String toString() {
		return "Die1 = " + die1 + ", Die2 = " + die2;
	}
}