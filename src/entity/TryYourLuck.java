package entity;

public class TryYourLuck extends Field {
	protected Player player;

	public TryYourLuck(String name) {
		super(name);
	}

	@Override
	public void landOnField(Player player) {
		GameBoard.drawCard(player);
	}

}
