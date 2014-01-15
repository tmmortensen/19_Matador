package testTools;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import boundaryToMatador.GUI;

import entity.*;

public class StreetTest {
	private Player player, owner;
	private GameBoard gameBoard;

	@Before
	public void setUp() throws Exception {
		this.gameBoard = new GameBoard();
		this.player = new Player(30000, "Anders And", 1);
		this.owner = new Player(30000, "Andersine", 2);
		((Ownable)gameBoard.getField(35)).diableMenu();
		this.player.setLocation(35);
	}

	@After
	public void tearDown() throws Exception {
		this.gameBoard = new GameBoard();
		this.player = new Player(30000, "Anders And", 1);
		this.owner = new Player(30000, "Andersine", 2);
		((Ownable)gameBoard.getField(35)).diableMenu();
		GUI.close();
	}

	@Test
	public void testEntities() {
		Assert.assertNotNull(this.player);
		Assert.assertNotNull(this.owner);
		Assert.assertNotNull(this.gameBoard);
	}

	@Test
	public void testLandOnField0Owned() {
		int expected = 30000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.gameBoard.landOnField(player);
		expected = 30000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testLandOnField1Owned() {
		((Ownable)gameBoard.getField(35)).buyField(this.owner);
		int expected = 30000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.gameBoard.landOnField(player);
		expected = 30000 - 560;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testLandOnField3Owned() {
		((Ownable)gameBoard.getField(32)).buyField(this.owner);
		((Ownable)gameBoard.getField(33)).buyField(this.owner);
		((Ownable)gameBoard.getField(35)).buyField(this.owner);
		int expected = 30000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.gameBoard.landOnField(player);
		expected = 30000 - (560 * 2);
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testLandOnField1House() {
		((Ownable)gameBoard.getField(32)).buyField(this.owner);
		((Ownable)gameBoard.getField(33)).buyField(this.owner);
		((Ownable)gameBoard.getField(35)).buyField(this.owner);
		((Street)gameBoard.getField(35)).buyHouse(35);
		int expected = 30000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.gameBoard.landOnField(player);
		expected = 30000 - 3000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testLandOnField2House() {
		((Ownable)gameBoard.getField(32)).buyField(this.owner);
		((Ownable)gameBoard.getField(33)).buyField(this.owner);
		((Ownable)gameBoard.getField(35)).buyField(this.owner);
		((Street)gameBoard.getField(35)).buyHouse(35);
		((Street)gameBoard.getField(35)).buyHouse(35);
		int expected = 30000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.gameBoard.landOnField(player);
		expected = 30000 - 9000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testLandOnField3House() {
		((Ownable)gameBoard.getField(32)).buyField(this.owner);
		((Ownable)gameBoard.getField(33)).buyField(this.owner);
		((Ownable)gameBoard.getField(35)).buyField(this.owner);
		((Street)gameBoard.getField(35)).buyHouse(35);
		((Street)gameBoard.getField(35)).buyHouse(35);
		((Street)gameBoard.getField(35)).buyHouse(35);
		int expected = 30000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.gameBoard.landOnField(player);
		expected = 30000 - 20000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testLandOnField4House() {
		((Ownable)gameBoard.getField(32)).buyField(this.owner);
		((Ownable)gameBoard.getField(33)).buyField(this.owner);
		((Ownable)gameBoard.getField(35)).buyField(this.owner);
		((Street)gameBoard.getField(35)).buyHouse(35);
		((Street)gameBoard.getField(35)).buyHouse(35);
		((Street)gameBoard.getField(35)).buyHouse(35);
		((Street)gameBoard.getField(35)).buyHouse(35);
		int expected = 30000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.gameBoard.landOnField(player);
		expected = 30000 - 24000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testLandOnFieldHotel() {
		((Ownable)gameBoard.getField(32)).buyField(this.owner);
		((Ownable)gameBoard.getField(33)).buyField(this.owner);
		((Ownable)gameBoard.getField(35)).buyField(this.owner);
		((Street)gameBoard.getField(35)).buyHouse(35);
		((Street)gameBoard.getField(35)).buyHouse(35);
		((Street)gameBoard.getField(35)).buyHouse(35);
		((Street)gameBoard.getField(35)).buyHouse(35);
		((Street)gameBoard.getField(35)).buyHouse(35);
		int expected = 30000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.gameBoard.landOnField(player);
		expected = 30000 - 28000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
}