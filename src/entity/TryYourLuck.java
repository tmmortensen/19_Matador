package entity;


public abstract class TryYourLuck extends Field {

	public TryYourLuck(String name) {
		super(name);
		this.name = name;
	}

	public void landOnField(int currentPlayer, Player[] players, int owned) {
		GameBoard.drawCard(currentPlayer, players);
	}
}
