package entity;

import boundary.Graphic;

/**
 * Class to create a player. This class can be used for storing a name and
 * account for a player.
 * 
 * @author DTU 02312 Gruppe 19, 2014
 * 
 */
public class Player {
	private final int CROSS_START_BONUS = 4000;
	
	private String name;
	private Account account;
	private boolean isBankrupt, isInJail, getsExtraTurn;
	private int location, numberOfIdentical, turnsInJail;

	/**
	 * Constructor that initiates name to a given name and set account to an initial score.
	 * Also sets all other values to 0/false
	 * 
	 * @param initialScore The score the player should start with
	 * @param name The name of the player
	 * @param playerNumber The number of the player
	 */
	public Player(int initialScore, String name, int playerNumber) {
		this.name = name;
		account = new Account(initialScore);
		isBankrupt = false;
		isInJail = false;
		getsExtraTurn = false;
		location = 1;
		numberOfIdentical = 0;
		turnsInJail = 0;
		
		Graphic.addPlayer(name, getAccountValue(), playerNumber);
	}

	/**
	 * Gets the name of the player
	 * 
	 * @return The name of this player.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set the bankrupt status, which is used to determine if a player
	 * is still in the game.
	 */
	public void setBankrupt() {
		isBankrupt = true;
	}

	/**
	 * Method to get the bankrupt status, which is used to determine if a player
	 * is still in the game.
	 * 
	 * @return True if player is bankrupt, otherwise false.
	 */
	public boolean isBankrupt() {
		return isBankrupt;
	}
	
	/**
	 * Checks if the player is in jail.
	 * 
	 * @return True if the player is in Jail, otherwise false
	 */
	public boolean isInJail() {
		return isInJail;
	}
	
	/**
	 * Checks if the player should get an extra turn
	 * 
	 * @return True if the player should get an extra turn, otherwise false
	 */
	public boolean getsExtraTurn() {
		return getsExtraTurn;
	}
	
	/**
	 * Puts the player in jail. Sets isInJail to true, and also moves the player to the 
	 * jail field, as well as updating the GUI
	 */
	public void goToJail() {
		isInJail = true;
		setLocation(11);
		turnsInJail = 0;
	}

	/**
	 * Method that takes the value of the dice and finds out what should happen 
	 * 
	 * @param sum The sum of the dice
	 * @param isIdentical Whether the dice shows identical values
	 */
	public void actOnDice(int sum, boolean isIdentical) {
		getsExtraTurn = false;
		
		if(isInJail) {
			actInJail(sum, isIdentical);
		}
		else {
			moveFieldsForward(sum);
		}
		
		checkIdentical(isIdentical);
	}
	
	/**
	 * Method to set the players current location (field number).
	 * 
	 * @param location The location to set the player to.
	 */
	public void setLocation(int newLocation) {
		if((newLocation+20) < location && !isInJail) {
			// Add the cross start bonus.
			// If the new locationnumber is lower than the old one, play must have crossed start or moved backwards
			// An offset of 20 is added to the comparison, to avoid small backwards moves (from Chance cards) from giving bonus.
			addToAccount(CROSS_START_BONUS);
		}
		
		location = newLocation;
		Graphic.moveCar(name, location);
	}

	/**
	 * Method to get the players current location (field number).
	 * 
	 * @return The players current location.
	 */
	public int getLocation() {
		return location;
	}
	
	/**
	 * Method to move a player forward on the board. Takes the players current
	 * location and adds a given number of fields.
	 * 
	 * @param fields The number of fields to move forward.
	 */
	public void moveFieldsForward(int fields) {
	    int newLocation = location + fields;
	    
		if (newLocation > 40) {
			newLocation = newLocation - 40;
		}
		else if(newLocation < 1) {
			newLocation = newLocation + 40;
		}
		
		setLocation(newLocation);
	}

	/**
	 * Method to get a players account value.
	 * 
	 * @return The players current account value.
	 */
	public int getAccountValue() {
		return account.getAccountValue();
	}

	/**
	 * Method to add to a players account value. Takes what the player has in
	 * the account and adds a given number.
	 * 
	 * @param amount
	 *            Amount to add to the account.
	 */
	public void addToAccount(int amount) {
		boolean succes = account.addToAccount(amount);

		if (!succes) {
			isBankrupt = true;
			account.setAccountValue(0);
		}
		
		Graphic.updatePlayer(name, getAccountValue());
	}

	/**
	 * Method to transfer "money" from one player to another. Also checks if the
	 * player has enough money for the transfer, and sets player to bankrupt if
	 * not.
	 * 
	 * @param player
	 *            Player to transfer to.
	 * @param amount
	 *            Amunt to transfer.
	 */
	public void transferTo(Player player, int amount) {
		boolean succes = account.addToAccount(-1 * amount);

		if (succes) {
			player.addToAccount(amount);
		} else {
			player.addToAccount(account.getAccountValue());
			account.setAccountValue(0);
			isBankrupt = true;
		}
		
		Graphic.updatePlayer(name, getAccountValue());
	}

	/**
	 * Method that makes a text with the most important values in the class, and
	 * some description.
	 * 
	 * @return A coherent string with values of name and account.
	 */
	public String toString() {
		return "Name = " + name + ", Account = " + account;
	}
	
	
	private void checkIdentical(boolean isIdentical) {
		if(isIdentical) {
			numberOfIdentical++;
			
			if(numberOfIdentical >= 3) {
				numberOfIdentical = 0;
				Graphic.goToJailMessage(name, true);
				goToJail();
			}
			else {
				getsExtraTurn = true;
			}
		}
		else {
			numberOfIdentical = 0;
		}
	}
	
	private void actInJail(int sum, boolean isIdentical) {
		if(isIdentical) {
			isInJail = false;
			moveFieldsForward(sum);
		}
		else {
			turnsInJail++;
			
			if(turnsInJail >= 3) {
				addToAccount(-1000);
				isInJail = false;
				moveFieldsForward(sum);
			}
			else if(Graphic.showJailOption(name)) {
				addToAccount(-1000);
				isInJail = false;
			}
		}
	}
}