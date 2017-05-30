package nicole.durability.io;

import static org.junit.Assert.*;

import java.lang.reflect.*;
import java.util.*;

import org.junit.*;

import nicole.durability.*;
import nicole.durability.actions.*;
import nicole.durability.mocks.*;
import nicole.test.*;

public class PencilWriterActionMenuTest extends TestHelper {

	private static final int EXPECTED_NUMBER_OF_MENU_ITEMS = 3;

	private Field inputScannerWrapperSingletonPrivateField;
	private MockInputScannerWrapperSingleton mockInputScanner;

	@Before
	public void setUp() throws Exception {

		this.inputScannerWrapperSingletonPrivateField = InputScannerWrapperSingleton.class
				.getDeclaredField("instance");
		this.inputScannerWrapperSingletonPrivateField.setAccessible(true);

		this.mockInputScanner = new MockInputScannerWrapperSingleton();
		this.inputScannerWrapperSingletonPrivateField.set(null, this.mockInputScanner);
	}

	@After
	public void tearDown() throws Exception {
		this.inputScannerWrapperSingletonPrivateField.set(null, null);
	}

	@Test
	public void testImplementsInterface() throws Exception {
		assertImplementsInterface(PencilWriterActionMenu.class,
				PencilWriterActionMenuInterface.class);
	}

	@Test
	public void testConstructor_setsUpActionsCorrectly() throws Exception {
		MockPencilWriterActionMenuDisplayHelper expectedDisplayHelper = new MockPencilWriterActionMenuDisplayHelper();
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				expectedDisplayHelper);

		PencilInterface expectedPencilPassedToActions = assertIsOfTypeAndGet(Pencil.class,
				pencilWriterActionMenu.getPencil());

		assertSame(expectedDisplayHelper, pencilWriterActionMenu.getDisplayHelper());

		List<MenuAction> actualMenuActions = pencilWriterActionMenu.getMenuActions();
		assertEquals(EXPECTED_NUMBER_OF_MENU_ITEMS, actualMenuActions.size());

		int expectedWriteIndex = 0;
		WriteAction actualWritePencilAction = checkWritePencilAction(actualMenuActions,
				expectedWriteIndex);
		assertSame(expectedPencilPassedToActions, actualWritePencilAction.getPencil());
		PaperInterface expectedPaperPassedToActions = assertIsOfTypeAndGet(Paper.class,
				actualWritePencilAction.getPaper());

		int expectedShowPaperIndex = 1;
		ShowPaperAction actualShowPaperAction = checkShowPaperAction(actualMenuActions,
				expectedShowPaperIndex);
		assertSame(expectedPaperPassedToActions, actualShowPaperAction.getPaper());

		int expectedEraseIndex = 2;
		EraseAction actualEraseAction = checkEraseAction(actualMenuActions, expectedEraseIndex);
		assertSame(expectedPencilPassedToActions, actualEraseAction.getPencil());
		assertSame(expectedPaperPassedToActions, actualEraseAction.getPaper());

	}

	private WriteAction checkWritePencilAction(List<MenuAction> actualMenuActions,
			int expectedWritePencilIndex) {
		MenuAction expectedWritePencilAction = actualMenuActions.get(expectedWritePencilIndex);
		WriteAction actualWritePencilAction = assertIsOfTypeAndGet(WriteAction.class,
				expectedWritePencilAction);

		PencilInterface actualPencil = assertIsOfTypeAndGet(Pencil.class,
				actualWritePencilAction.getPencil());

		int actualDurability = actualPencil.getCurrentDurability();
		int expectedDurability = 500;
		assertEquals(expectedDurability, actualDurability);

		int actualPencilLength = actualPencil.getPencilLength();
		int expectedPencilLength = 4;
		assertEquals(expectedPencilLength, actualPencilLength);

		assertIsOfTypeAndGet(Paper.class, actualWritePencilAction.getPaper());

		return actualWritePencilAction;
	}

	private ShowPaperAction checkShowPaperAction(List<MenuAction> actualMenuActions,
			int expectedShowPaperIndex) {
		MenuAction expectedShowPaperAction = actualMenuActions.get(expectedShowPaperIndex);
		ShowPaperAction actualShowPaperAction = assertIsOfTypeAndGet(ShowPaperAction.class,
				expectedShowPaperAction);
		assertIsOfTypeAndGet(Paper.class, actualShowPaperAction.getPaper());

		return actualShowPaperAction;
	}

	private EraseAction checkEraseAction(List<MenuAction> actualMenuActions,
			int expectedEraseIndex) {
		MenuAction expectedEraseAction = actualMenuActions.get(expectedEraseIndex);
		EraseAction actualEraseAction = assertIsOfTypeAndGet(EraseAction.class,
				expectedEraseAction);

		return actualEraseAction;
	}

	@Test
	public void testOpenMainMenu_passesPencilActionsAndPencilToDisplayHelper() throws Exception {
		MockPencilWriterActionMenuDisplayHelper mockDisplayHelper = new MockPencilWriterActionMenuDisplayHelper();
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				mockDisplayHelper);

		List<MenuAction> expectedPencilActions = pencilWriterActionMenu.getMenuActions();
		expectedPencilActions.clear();
		expectedPencilActions.add(new MockMenuAction());
		expectedPencilActions.add(new MockMenuAction());
		expectedPencilActions.add(new MockMenuAction());

		pencilWriterActionMenu.openMainMenu();

		assertTrue(mockDisplayHelper.displayPencilStatsWasCalled());

		PencilInterface expectedPencil = assertIsOfTypeAndGet(Pencil.class,
				pencilWriterActionMenu.getPencil());
		PencilInterface actualPencilPassedToDisplayStats = mockDisplayHelper
				.getPencilPassedToDisplayPencilStats();
		assertSame(expectedPencil, actualPencilPassedToDisplayStats);

		List<MenuAction> actualPencilActions = mockDisplayHelper.getActionListPassedToDisplay();

		assertSame(expectedPencilActions, actualPencilActions);
		for (MenuAction pencilAction : expectedPencilActions) {
			assertTrue(actualPencilActions.contains(pencilAction));
		}
	}

	@Test
	public void testOpenMainMenu_takesNoActionIfInputIsNotANumber() throws Exception {
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				new MockPencilWriterActionMenuDisplayHelper());

		List<MenuAction> expectedPencilActions = pencilWriterActionMenu.getMenuActions();
		expectedPencilActions.clear();
		MockMenuAction theOnlyPencilAction = new MockMenuAction();
		expectedPencilActions.add(theOnlyPencilAction);

		this.mockInputScanner.setTextReturnedFromNextLine("I am not a number!");
		try {
			pencilWriterActionMenu.openMainMenu();
		} catch (NumberFormatException exception) {
			assertFail("Did you handle the NumberFormatException?");
		}

		assertFalse(theOnlyPencilAction.performWasCalled());
	}

	@Test
	public void testOpenMainMenu_takesNoActionIfInputNumberIsTooLarge() throws Exception {
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				new MockPencilWriterActionMenuDisplayHelper());

		List<MenuAction> expectedPencilActions = pencilWriterActionMenu.getMenuActions();
		expectedPencilActions.clear();
		MockMenuAction theOnlyPencilAction = new MockMenuAction();
		expectedPencilActions.add(theOnlyPencilAction);

		String numberThatIsTooLarge = "2";
		this.mockInputScanner.setTextReturnedFromNextLine(numberThatIsTooLarge);
		try {
			pencilWriterActionMenu.openMainMenu();
		} catch (IndexOutOfBoundsException exception) {
			assertFail("Did you handle the IndexOutOfBoundsException?");
		}

		assertFalse(theOnlyPencilAction.performWasCalled());
	}

	@Test
	public void testOpenMainMenu_takesNoActionIfInputNumberIsTooSmall() throws Exception {
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				new MockPencilWriterActionMenuDisplayHelper());

		List<MenuAction> expectedPencilActions = pencilWriterActionMenu.getMenuActions();
		expectedPencilActions.clear();
		MockMenuAction theOnlyPencilAction = new MockMenuAction();
		expectedPencilActions.add(theOnlyPencilAction);

		String numberThatIsTooSmall = "0";
		this.mockInputScanner.setTextReturnedFromNextLine(numberThatIsTooSmall);
		try {
			pencilWriterActionMenu.openMainMenu();
		} catch (IndexOutOfBoundsException exception) {
			assertFail("Did you handle the IndexOutOfBoundsException?");
		}

		assertFalse(theOnlyPencilAction.performWasCalled());
	}

	@Test
	public void testOpenMainMenu_performsPencilActionBasedOnUserChoice_firstAction()
			throws Exception {
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				new MockPencilWriterActionMenuDisplayHelper());

		MockMenuAction mockAction1 = new MockMenuAction();
		MockMenuAction mockAction2 = new MockMenuAction();
		MockMenuAction mockAction3 = new MockMenuAction();
		List<MenuAction> expectedPencilActions = pencilWriterActionMenu.getMenuActions();

		expectedPencilActions.clear();
		expectedPencilActions.add(mockAction1);
		expectedPencilActions.add(mockAction2);
		expectedPencilActions.add(mockAction3);

		assertFalse(this.mockInputScanner.nextLineWasCalled());

		this.mockInputScanner.setTextReturnedFromNextLine("1");
		pencilWriterActionMenu.openMainMenu();

		assertTrue(this.mockInputScanner.nextLineWasCalled());
		assertTrue(mockAction1.performWasCalled());

		assertFalse(mockAction2.performWasCalled());
		assertFalse(mockAction3.performWasCalled());
	}

	@Test
	public void testOpenMainMenu_performsPencilActionBasedOnUserChoice_secondAction()
			throws Exception {
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				new MockPencilWriterActionMenuDisplayHelper());

		MockMenuAction mockAction1 = new MockMenuAction();
		MockMenuAction mockAction2 = new MockMenuAction();
		MockMenuAction mockAction3 = new MockMenuAction();
		List<MenuAction> expectedPencilActions = pencilWriterActionMenu.getMenuActions();

		expectedPencilActions.clear();
		expectedPencilActions.add(mockAction1);
		expectedPencilActions.add(mockAction2);
		expectedPencilActions.add(mockAction3);

		assertFalse(this.mockInputScanner.nextLineWasCalled());

		this.mockInputScanner.setTextReturnedFromNextLine("2");
		pencilWriterActionMenu.openMainMenu();

		assertTrue(this.mockInputScanner.nextLineWasCalled());
		assertTrue(mockAction2.performWasCalled());

		assertFalse(mockAction1.performWasCalled());
		assertFalse(mockAction3.performWasCalled());
	}

	@Test
	public void testOpenMainMenu_performsPencilActionBasedOnUserChoice_thirdAction()
			throws Exception {
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				new MockPencilWriterActionMenuDisplayHelper());

		MockMenuAction mockAction1 = new MockMenuAction();
		MockMenuAction mockAction2 = new MockMenuAction();
		MockMenuAction mockAction3 = new MockMenuAction();
		List<MenuAction> expectedPencilActions = pencilWriterActionMenu.getMenuActions();

		expectedPencilActions.clear();
		expectedPencilActions.add(mockAction1);
		expectedPencilActions.add(mockAction2);
		expectedPencilActions.add(mockAction3);

		assertFalse(this.mockInputScanner.nextLineWasCalled());

		this.mockInputScanner.setTextReturnedFromNextLine("3");
		pencilWriterActionMenu.openMainMenu();

		assertTrue(this.mockInputScanner.nextLineWasCalled());
		assertTrue(mockAction3.performWasCalled());

		assertFalse(mockAction1.performWasCalled());
		assertFalse(mockAction2.performWasCalled());
	}
}
