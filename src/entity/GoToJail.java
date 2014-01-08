package entity;

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
	public GoToJail(String name, GameBoard gameBoard) {
		super(name, gameBoard);
	}

	@Override
	/**
	 * Overrider fra Field
	 */
	public void landOnField(Player player) {
		player.goToJail();
	}
}
