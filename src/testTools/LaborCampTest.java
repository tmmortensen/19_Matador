package testTools;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.LaborCamp;
import entity.GameBoard;
import entity.Player;

public class LaborCampTest {
	private Player player;
	private Player owner;
	private GameBoard gameBoard;

	@Before
	public void setUp() throws Exception {
		this.gameBoard = new GameBoard(16);
		this.player = new Player(5000, "Anders And");
		this.owner = new Player(1000, "Andersine");
		
		this.gameBoard.setField(new LaborCamp("LaborCamp1", 100, 0, gameBoard), 14);
		this.gameBoard.setField(new LaborCamp("LaborCamp2", 100, 0, gameBoard), 15);
		this.gameBoard.shakeDieCup();
		
		this.owner.setLocation(14);
		this.gameBoard.setOwner(owner);
		
		this.player.setLocation(14);
	}

	@After
	public void tearDown() throws Exception {
		this.player = new Player(5000, "Anders And");
		this.owner = new Player(1000, "Andersine");
		this.gameBoard.setField(new LaborCamp("LaborCamp2", 100, 0, gameBoard), 15);
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
		expected = 5000 - (1 * 100 * this.gameBoard.getDieCupSum());
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
		expected = 5000 - (2 * 100 * this.gameBoard.getDieCupSum());
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
}