package entity;

public abstract class TryYourLuck extends Field {

	public TryYourLuck(String name) {
		super(name);
	}

	@Override
	public void landOnField(Player player) {
		GameBoard.drawCard(currentPlayer, players);
	}

}
