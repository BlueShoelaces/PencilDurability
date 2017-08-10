package nicole.durability.actions;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import nicole.test.*;

public class EditActionTest extends TestHelper {

	@Test
	public void testImplementsMenuAction() throws Exception {
		assertImplementsInterface(EditAction.class, MenuAction.class);
	}

	@Test
	public void testGetMenuTextToDisplay() throws Exception {
		EditAction editAction = new EditAction();
		String actualMenuTextToDisplay = editAction.getMenuTextToDisplay();
		String expectedMenuTextToDisplay = "Write over previously erased text";

		assertEquals(expectedMenuTextToDisplay, actualMenuTextToDisplay);
	}
}
