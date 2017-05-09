package nicole.durability.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.*;

import org.junit.*;

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
		assertIsOfTypeAndGet(WritePencilAction.class, firstMenuAction);
	}

}
