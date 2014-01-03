package entity;

public class TryYourLuck extends Field {
	protected Player player;

	public TryYourLuck(String name) {
		super(name);
		// landOnField(player);
	}

	@Override
	public void landOnField(Player player) {
		GameBoard.drawCard(player);
	}

}
