package nicole.durability.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.*;

import org.junit.*;

import nicole.durability.*;
import nicole.durability.actions.*;
import nicole.test.*;

public class PencilWriterActionMenuTest extends TestHelper {

	private static final int NUMBER_OF_MENU_ITEMS = 1;

	@Test
	public void testConstructorCreatesEmptyActionList() throws Exception {
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu();
		List<WritePencilAction> menuActions = pencilWriterActionMenu.getMenuActions();
		assertNotNull(menuActions);
		assertEquals(0, menuActions.size());
	}

	@Test
	public void testOpenMainMenu_createsWritePencilAction() throws Exception {
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu();
		pencilWriterActionMenu.openMainMenu();

		List<WritePencilAction> actualMenuActions = pencilWriterActionMenu.getMenuActions();
		assertEquals(NUMBER_OF_MENU_ITEMS, actualMenuActions.size());

		WritePencilAction firstMenuAction = actualMenuActions.get(0);
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
}
