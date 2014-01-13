package testTools;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.Brewery;
import entity.GameBoard;
import entity.Player;

public class LaborCampTest {
	private Player player;
	private Player owner;
	private GameBoard gameBoard;

	@Before
	public void setUp() throws Exception {
		this.gameBoard = new GameBoard();
		this.player = new Player(5000, "Anders And", 1);
		this.owner = new Player(1000, "Andersine", 2);

		//this.gameBoard.setField(new Brewery("LaborCamp1", 100, 0, gameBoard), 14);
		//this.gameBoard.setField(new Brewery("LaborCamp2", 100, 0, gameBoard), 15);
		this.gameBoard.getDieCup().shakeDieCup();

		this.owner.setLocation(14);
		this.gameBoard.setOwner(owner);

		this.player.setLocation(14);
	}

	@After
	public void tearDown() throws Exception {
		this.player = new Player(5000, "Anders And", 1);
		this.owner = new Player(1000, "Andersine", 2);
		//this.gameBoard.setField(new Brewery("LaborCamp2", 100, 0, gameBoard), 15);
	}

	@Test
	public void testEntities() {
		Assert.assertNotNull(this.player);
		Assert.assertNotNull(this.owner);
		Assert.assertNotNull(this.gameBoard);
	}

	@Test
	public void testLandOnField1Owned() {
		int expected = 5000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.gameBoard.landOnField(this.player);
		expected = 5000 - (1 * 100 * this.gameBoard.getDieCup().getSum());
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testLandOnField2Owned() {
		int expected = 5000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.owner.setLocation(15);
		this.gameBoard.setOwner(owner);

		this.gameBoard.landOnField(this.player);
		expected = 5000 - (2 * 100 * this.gameBoard.getDieCup().getSum());
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
}