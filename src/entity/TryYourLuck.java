package entity;

public class TryYourLuck extends Field {
	GameBoard gameBoard;
	
	public TryYourLuck(String name, GameBoard gameBoard) {
		super(name);
		this.gameBoard = gameBoard;
	}

	public void landOnField(Player player) {
		gameBoard.drawCard(player);
	}

}
