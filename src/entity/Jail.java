package entity;

import control.Game;

/**
 * Fængslet, afvikler fængselslogikken
 * 
 * @author Gruppe 16
 * 
 */
public class Jail extends Field {

	/**
	 * Videresender navnet på feltet
	 * 
	 * @param name
	 *            Feltets navn
	 */
	public Jail(String name) {
		super(name);
	}

	@Override
	/**
	 * Overrider Field
	 */
	public void landOnField(Player players) {
		// "true" hvis spilleren er i fængsel
		if (players.inJail()) {
			// "true" hvis spilleren slår to ens
			if (Game.getIdentical()) {
				// nulstiller spilleren "ens slag"
				players[currentPlayer].setNumOfThrows(0);
				// metoden jainTransfer bliver kaldt på spilleren, som kommer ud
				// af fængslet
				players.jailTransfer();
			} else {
				players.setNumOfThrows(players.getNumOfThrows() + 1);
			}

			if (players.getNumOfThrows() > 2) {
				players.withdraw(1000);
				players.jailTransfer();
				players.setNumOfThrows(0);
			}
		}
	}
}
