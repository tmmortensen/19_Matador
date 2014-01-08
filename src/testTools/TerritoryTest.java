package testTools;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.Field;
import entity.Ownable;
import entity.Player;
import entity.Street;

public class TerritoryTest {
	private Player player;
	private Player owner;
	private Field ter200;
	private Field ter0;

	@Before
	public void setUp() throws Exception {
		this.player = new Player(1000, "Anders And", 1);
		this.owner = new Player(1000, "Andersine", 2);
		//this.ter200 = new Street("Territory +200", 200, 200);
		//this.ter0 = new Street("Territory 0", 0, 0);
		((Ownable) ter200).setOwner(owner); // Cast to ownable to be able to set
											// owner
		((Ownable) ter0).setOwner(owner); // Cast to ownable to be able to set
											// owner
	}

	@After
	public void tearDown() throws Exception {
		this.player = new Player(1000, "Anders And", 0);
		this.owner = new Player(1000, "Andersine", 0);
		// The fields are unaltered
	}

	@Test
	public void testEntities() {
		Assert.assertNotNull(this.player);
		Assert.assertNotNull(this.ter200);
		Assert.assertNotNull(this.ter0);
		Assert.assertTrue(this.ter200 instanceof Street);
		Assert.assertTrue(this.ter0 instanceof Street);
	}

	@Test
	public void testLandOnField200() {
		int expected = 1000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.ter200.landOnField(this.player);
		expected = 1000 - 200;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testLandOnField200Twice() {

		int expected = 1000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.ter200.landOnField(this.player);
		this.ter200.landOnField(this.player);
		expected = 1000 - 200 - 200;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testLandOnField0() {
		int expected = 1000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.ter0.landOnField(this.player);
		expected = 1000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testLandOnField0Twice() {
		int expected = 1000;
		int actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		this.ter0.landOnField(this.player);
		this.ter0.landOnField(this.player);
		expected = 1000;
		actual = this.player.getAccountValue();
		Assert.assertEquals(expected, actual);
	}
}