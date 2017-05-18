package nicole.durability.actions;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import nicole.test.*;

public class EraseActionTest extends TestHelper {

	@Test
	public void testImplementsInterface() throws Exception {
		assertImplementsInterface(EraseAction.class, MenuAction.class);
	}

	@Test
	public void testMenuTextToDisplay() throws Exception {
		EraseAction eraseAction = new EraseAction();
		String expectedMenuText = "Erase some text";
		String actualMenuText = eraseAction.getMenuTextToDisplay();
		assertEquals(expectedMenuText, actualMenuText);
	}
}
