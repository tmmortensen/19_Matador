package entity;

/**
 * Class to create an Account. This class can be used to store a number representing an account value.
 *
 * @author DTU 02312 Gruppe 19, 2014
 *
 */
public class Account {
	private int accountValue;

	/**
	 * Constructor to create a new account. Takes no arguments, and sets the initial account value to 0.
	 */
	public Account() {
		accountValue = 0;
	}

	/**
	 * Constructor to create a new account. Takes an argument for initial account value.
	 *
	 * @param initialAccountValue The value to set the new account to.
	 */
	public Account(int initialAccountValue) {
		accountValue = initialAccountValue;
	}

	/**
	 * Method to set the account.
	 * Checks if the account will go below 0 before setting it.
	 *
	 * @param input The value to set the account to.
	 * @return True if the account was set correctly. False if the account was not set, because the input value was below 0.
	 */
	public boolean setAccountValue(int input) {
		if(input >= 0) {
			accountValue = input;
			return true;
		}

		return false;
	}

	/**
	 * Method to get the value of the account.
	 *
	 * @return The value of the account.
	 */
	public int getAccountValue() {
		return accountValue;
	}

	/**
	 * A method to add to the account, so the resulting value will be the existing value plus the input value.
	 * Checks if the account will go below 0 before adding to it.
	 *
	 * @param input The value to add.
	 * @return True if the account was set correctly. False if the account was not set, because the value would have gone below 0.
	 */
	public boolean addToAccount(int input) {
		if(accountValue + input >= 0) {
			accountValue = accountValue + input;
			return true;
		}

		return false;
	}

	/**
	 * A method to get the contents of the account as a string.
	 *
	 * @return The account value as a string.
	 */
	public String toString() {
		return Integer.toString(accountValue);
	}
}