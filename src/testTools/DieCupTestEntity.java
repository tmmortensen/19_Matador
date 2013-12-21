package testTools;

/**
 * Class to create a "cheating" diecup. This class will not actually use dice, but just give a hardcoded number every time.
 *
 * @author DTU 02312 Gruppe 19
 *
 */
public class DieCupTestEntity {
	private final int VALUE_TO_GIVE1 = 1;
	private final int VALUE_TO_GIVE2 = 2;
	
	/**
	 * Constructor
	 */
    public DieCupTestEntity() {
    	
    }

    /**
     * Method to shake the diecup.
     * This won't do anything in this class, since the values are final. It just have to be there, to make
     * it possible to substitute the "real" DieCup with this "false" one.
     */
    public void shakeDieCup() {
    	//Does nothing, since the values are fixed
    }

    /**
     * Adds value from die1 and die2.
     *
     * @return The sum of die1 and die2.
     */
    public int getSum() {
        return VALUE_TO_GIVE1 + VALUE_TO_GIVE2;
    }
    
    /**
     * Get faceValue from die1
     *
     * @return The current value of die1.
     */
    public int getValueDie1() {
        return VALUE_TO_GIVE1;
    }
    
    /**
     * Get faceValue from die2
     *
     * @return The current value of die2.
     */
    public int getValueDie2() {
        return VALUE_TO_GIVE2;
    }

    /**
     * Checks if the values of the dice are equal.
     *
     * @return True if facevalues of die1 and die2 are the same, otherwise False.
     */
    public boolean getEns() {
        if (VALUE_TO_GIVE1 == VALUE_TO_GIVE2) {
            return true;
        }
        return false;
    }
    
    /**
     * Method that makes a text with the most important values in the class, and some description.
     *
     * @return A coherent string with values of Die1 and Die2
     */
    public String toString() {
        return "Die1 = " + VALUE_TO_GIVE1 + ", Die2 = " + VALUE_TO_GIVE2;
    }
}