package nicole.durability.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.*;

import org.junit.*;

import nicole.durability.*;
import nicole.durability.actions.*;
import nicole.durability.mocks.*;
import nicole.test.*;

public class PencilWriterActionMenuTest extends TestHelper {

	private static final int NUMBER_OF_MENU_ITEMS = 1;

	@Test
	public void testConstructor() throws Exception {
		MockPencilWriterActionMenuDisplayHelper expectedDisplayHelper = new MockPencilWriterActionMenuDisplayHelper();
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				expectedDisplayHelper);
		List<PencilAction> emptyPencilActionList = pencilWriterActionMenu.getMenuActions();
		assertNotNull(emptyPencilActionList);
		assertEquals(0, emptyPencilActionList.size());

		assertSame(expectedDisplayHelper, pencilWriterActionMenu.getDisplayHelper());
	}

	@Test
	public void testOpenMainMenu_createsWritePencilAction() throws Exception {
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				new MockPencilWriterActionMenuDisplayHelper());
		pencilWriterActionMenu.openMainMenu();

		List<PencilAction> actualMenuActions = pencilWriterActionMenu.getMenuActions();
		assertEquals(NUMBER_OF_MENU_ITEMS, actualMenuActions.size());

		PencilAction firstMenuAction = actualMenuActions.get(0);
		WritePencilAction writePencilAction = assertIsOfTypeAndGet(WritePencilAction.class,
				firstMenuAction);

		Pencil pencil = assertIsOfTypeAndGet(Pencil.class, writePencilAction.getPencil());

		int actualDurability = pencil.getCurrentDurability();
		int expectedDurability = 500;
		assertEquals(expectedDurability, actualDurability);

		int actualPencilLength = pencil.getPencilLength();
		int expectedPencilLength = 4;
		assertEquals(expectedPencilLength, actualPencilLength);

		assertIsOfTypeAndGet(Paper.class, writePencilAction.getPaper());
	}

	@Test
	public void testOpenMainMenu_passesPencilActionsToDisplayHelper() throws Exception {
		MockPencilWriterActionMenuDisplayHelper mockDisplayHelper = new MockPencilWriterActionMenuDisplayHelper();
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				mockDisplayHelper);

		pencilWriterActionMenu.openMainMenu();

		List<PencilAction> expectedPencilActions = pencilWriterActionMenu.getMenuActions();
		List<PencilAction> actualPencilActions = mockDisplayHelper.getActionListPassedToDisplay();

		assertSame(expectedPencilActions, actualPencilActions);
	}
}
