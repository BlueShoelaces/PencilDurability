package nicole.durability.actions;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import nicole.test.*;

public class ShowPaperActionTest extends TestHelper {

	@Test
	public void testImplementsInterface() throws Exception {
		assertImplementsInterface(ShowPaperAction.class, PencilAction.class);
	}

	@Test
	public void testGetMenuTextToDisplay() throws Exception {
		ShowPaperAction showPaperAction = new ShowPaperAction();
		String actualMenuText = showPaperAction.getMenuTextToDisplay();
		String expectedMenuText = "Show paper";
		assertEquals(expectedMenuText, actualMenuText);
	}

}
