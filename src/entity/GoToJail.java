package entity;

import boundary.Graphic;

/**
 * Feltet gotojail, som sender spilleren i fængsel
 * 
 * @author Gruppe 16
 * 
 */
public class GoToJail extends Field {

	/**
	 * Videresender feltets navn
	 * 
	 * @param name
	 *            Feltets navn
	 */
	public GoToJail(String name) {
		super(name);
	}

	@Override
	/**
	 * Overrider fra Field
	 */
	public void landOnField(Player players) {
		players.setLocation(11);
		Graphic.moveCar(getName(), 11);
		players.jailTransfer();
	}
}
