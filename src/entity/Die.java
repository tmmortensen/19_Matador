package entity;

import java.util.Random;

/**
 * Class to create a die. This class can be used to generate random numbers from 1 to 6.
 *
 * @author DTU 02312 Gruppe 19, 2014
 *
 */
public class Die {

    private final int MAX_VALUE = 6;
    private int faceValue;
    private Random random;

    /**
     * Constructor to set the facevalue of the die and instanciate the random generator.
     */
    public Die() {
        random = new Random();
        faceValue = 0;
    }

    /**
     * Method to roll the die, and give it a new value.
     */
    public void roll() {
        //Generator makes numbers from 0 to 5, so we add 1 to get 1 to 6
        faceValue = random.nextInt(MAX_VALUE) + 1;
    }

    /**
     * Method so you can get the facevalue.
     *
     * @return The current facevalue of the die.
     */
    public int getValue() {
        return faceValue;
    }

    /**
     * Method that makes a string of facevalue to print.
     *
     * @return The current facevalue of the die as a string.
     */
    public String toString() {
        return Integer.toString(faceValue);
    }
}