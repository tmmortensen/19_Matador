package testTools;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.Shipping;
import entity.GameBoard;
import entity.Player;

public class FleetTest {
	private Player player;
	private Player owner;
	private GameBoard gameBoard;

	@Before
	public void setUp() throws Exception {
		this.gameBoard = new GameBoard(22);
		this.player = new Player(5000, "Anders And");
		this.owner = new Player(1000, "Andersine");
		
		this.gameBoard.setField(new Shipping("Fleet1", 0, gameBoard), 18);
		this.gameBoard.setField(new Shipping("Fleet2", 0, gameBoard), 19);
		this.gameBoard.setField(new Shipping("Fleet3", 0, gameBoard), 20);
		this.gameBoard.setField(new Shipping("Fleet4", 0, gameBoard), 21);
		
		this.owner.setLocation(18);
		this.gameBoard.setOwner(owner);
		
		this.player.setLocation(18);
	}

	@After
	public void tearDown() throws Exception {
		this.player = new Player(5000, "Anders And");
		this.owner = new Player(1000, "Andersine");
		this.gameBoard.setField(new Shipping("Fleet2", 0, gameBoard), 19);
		this.gameBoard.setField(new Shipping("Fleet3", 0, gameBoard), 20);
		this.gameBoard.setField(new Shipping("Fleet4", 0, gameBoard), 21);
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
		expected = 5000 - 500;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testLandOnField2Owned() {
		int expected = 5000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.owner.setLocation(19);
		this.gameBoard.setOwner(owner);
		
		this.gameBoard.landOnField(this.player);
		expected = 5000 - 1000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testLandOnField3Owned() {
		int expected = 5000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.owner.setLocation(19);
		this.gameBoard.setOwner(owner);
		this.owner.setLocation(20);
		this.gameBoard.setOwner(owner);
		
		this.gameBoard.landOnField(this.player);
		expected = 5000 - 2000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testLandOnField4Owned() {
		int expected = 5000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.owner.setLocation(19);
		this.gameBoard.setOwner(owner);
		this.owner.setLocation(20);
		this.gameBoard.setOwner(owner);
		this.owner.setLocation(21);
		this.gameBoard.setOwner(owner);
		
		this.gameBoard.landOnField(this.player);
		expected = 5000 - 4000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
}