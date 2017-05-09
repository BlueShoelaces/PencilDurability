package nicole.durability.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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
		assertSame(expectedDisplayHelper, pencilWriterActionMenu.getDisplayHelper());

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

		List<PencilAction> expectedPencilActions = pencilWriterActionMenu.getMenuActions();
		expectedPencilActions.clear();
		expectedPencilActions.add(new MockPencilAction());
		expectedPencilActions.add(new MockPencilAction());
		expectedPencilActions.add(new MockPencilAction());

		pencilWriterActionMenu.openMainMenu();

		List<PencilAction> actualPencilActions = mockDisplayHelper.getActionListPassedToDisplay();

		assertSame(expectedPencilActions, actualPencilActions);
		for (PencilAction pencilAction : expectedPencilActions) {
			assertTrue(actualPencilActions.contains(pencilAction));
		}
	}

	@Test
	public void testOpenMainMenu_performsFirstPencilAction() throws Exception {
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				new MockPencilWriterActionMenuDisplayHelper());

		List<PencilAction> menuActions = pencilWriterActionMenu.getMenuActions();
		menuActions.clear();
		MockPencilAction mockPencilAction = new MockPencilAction();
		menuActions.add(mockPencilAction);

		pencilWriterActionMenu.openMainMenu();

		boolean performWasCalled = mockPencilAction.performWasCalled();
		assertTrue(performWasCalled);
	}
}
