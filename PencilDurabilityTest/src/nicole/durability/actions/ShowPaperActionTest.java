package nicole.durability.actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import nicole.durability.mocks.*;
import nicole.test.*;

public class ShowPaperActionTest extends TestHelper {

	@Test
	public void testImplementsInterface() throws Exception {
		assertImplementsInterface(ShowPaperAction.class, MenuAction.class);
	}

	@Test
	public void testConstructor() throws Exception {
		MockPaper expectedPaper = new MockPaper();
		ShowPaperAction showPaperAction = new ShowPaperAction(expectedPaper);

		assertSame(expectedPaper, showPaperAction.getPaper());
	}

	@Test
	public void testGetMenuTextToDisplay() throws Exception {
		ShowPaperAction showPaperAction = new ShowPaperAction(new MockPaper());
		String actualMenuText = showPaperAction.getMenuTextToDisplay();
		String expectedMenuText = "Show paper";
		assertEquals(expectedMenuText, actualMenuText);
	}

	@Test
	public void testPerform_showsPaper() throws Exception {
		MockPaper mockPaper = new MockPaper();
		ShowPaperAction showPaperAction = new ShowPaperAction(mockPaper);

		showPaperAction.perform();

		assertTrue(mockPaper.showPaperWasCalled());
	}
}
