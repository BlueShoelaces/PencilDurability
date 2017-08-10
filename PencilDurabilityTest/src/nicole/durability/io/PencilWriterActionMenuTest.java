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

	private static final int EXPECTED_NUMBER_OF_MENU_ITEMS = 5;

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

		int expectedShowPaperIndex = 0;
		ShowPaperAction actualShowPaperAction = checkShowPaperAction(actualMenuActions,
				expectedShowPaperIndex);
		PaperInterface expectedPaperPassedToActions = assertIsOfTypeAndGet(Paper.class,
				actualShowPaperAction.getPaper());

		int expectedWriteIndex = 1;
		WriteAction actualWritePencilAction = checkWriteAction(actualMenuActions,
				expectedWriteIndex);
		assertSame(expectedPencilPassedToActions, actualWritePencilAction.getPencil());
		assertSame(expectedPaperPassedToActions, actualWritePencilAction.getPaper());

		int expectedEraseIndex = 2;
		EraseAction actualEraseAction = checkEraseAction(actualMenuActions, expectedEraseIndex);
		assertSame(expectedPencilPassedToActions, actualEraseAction.getPencil());
		assertSame(expectedPaperPassedToActions, actualEraseAction.getPaper());

		int expectedEditIndex = 3;
		EditAction actualEditAction = checkEditAction(actualMenuActions, expectedEditIndex);
		assertSame(expectedPencilPassedToActions, actualEditAction.getPencil());
		assertSame(expectedPaperPassedToActions, actualEditAction.getPaper());

		int expectedSharpenIndex = 4;
		SharpenPencilAction sharpenPencilAction = checkSharpenPencilAction(actualMenuActions,
				expectedSharpenIndex);
		assertSame(expectedPencilPassedToActions, sharpenPencilAction.getPencil());

	}

	private EditAction checkEditAction(List<MenuAction> actualMenuActions, int expectedEditIndex) {

		MenuAction expectedEditAction = actualMenuActions.get(expectedEditIndex);
		EditAction actualEditAction = assertIsOfTypeAndGet(EditAction.class, expectedEditAction);

		return actualEditAction;
	}

	private SharpenPencilAction checkSharpenPencilAction(List<MenuAction> actualMenuActions,
			int expectedSharpenIndex) {
		MenuAction expectedSharpenPencilAction = actualMenuActions.get(expectedSharpenIndex);
		SharpenPencilAction actualSharpenPencilAction = assertIsOfTypeAndGet(
				SharpenPencilAction.class, expectedSharpenPencilAction);
		return actualSharpenPencilAction;
	}

	private WriteAction checkWriteAction(List<MenuAction> actualMenuActions,
			int expectedWritePencilIndex) {
		MenuAction expectedWritePencilAction = actualMenuActions.get(expectedWritePencilIndex);
		WriteAction actualWritePencilAction = assertIsOfTypeAndGet(WriteAction.class,
				expectedWritePencilAction);

		PencilInterface actualPencil = assertIsOfTypeAndGet(Pencil.class,
				actualWritePencilAction.getPencil());

		int actualDurability = actualPencil.getCurrentPencilDurability();
		int expectedDurability = 500;
		assertEquals(expectedDurability, actualDurability);

		int actualPencilLength = actualPencil.getPencilLength();
		int expectedPencilLength = 4;
		assertEquals(expectedPencilLength, actualPencilLength);

		int actualEraserDurability = actualPencil.getEraserDurability();
		int expectedEraserDurability = 10;
		assertEquals(expectedEraserDurability, actualEraserDurability);

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

		pencilWriterActionMenu.openMainMenuWithQuitOption();

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

		boolean actualShouldQuitValue = true;
		this.mockInputScanner.setTextReturnedFromNextLine("I am not a number!");
		try {
			actualShouldQuitValue = pencilWriterActionMenu.openMainMenuWithQuitOption();
		} catch (NumberFormatException exception) {
			assertFail("Did you handle the NumberFormatException?");
		}

		assertFalse(theOnlyPencilAction.performWasCalled());
		assertFalse(actualShouldQuitValue);
	}

	@Test
	public void testOpenMainMenu_returnsFalseByDefault() throws Exception {
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				new MockPencilWriterActionMenuDisplayHelper());

		assertFalse(pencilWriterActionMenu.openMainMenuWithQuitOption());
	}

	@Test
	public void testOpenMainMenu_returnsTrueIfQuitIsSelected() throws Exception {
		String quitSelection = "0";
		this.mockInputScanner.setTextReturnedFromNextLine(quitSelection);
		boolean expectedShouldQuitValue = true;

		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				new MockPencilWriterActionMenuDisplayHelper());

		boolean actualShouldQuitValue = pencilWriterActionMenu.openMainMenuWithQuitOption();

		assertEquals(expectedShouldQuitValue, actualShouldQuitValue);
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
			pencilWriterActionMenu.openMainMenuWithQuitOption();
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
			pencilWriterActionMenu.openMainMenuWithQuitOption();
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
		List<MenuAction> expectedMenuActions = pencilWriterActionMenu.getMenuActions();

		expectedMenuActions.clear();
		expectedMenuActions.add(mockAction1);
		expectedMenuActions.add(mockAction2);
		expectedMenuActions.add(mockAction3);

		int userSelection = 1;
		checkPerformWasCalledOnlyOnActionIndicatedByUser(pencilWriterActionMenu,
				expectedMenuActions, userSelection);
	}

	@Test
	public void testOpenMainMenu_performsPencilActionBasedOnUserChoice_actionInTheMiddle()
			throws Exception {
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				new MockPencilWriterActionMenuDisplayHelper());

		MockMenuAction mockAction1 = new MockMenuAction();
		MockMenuAction mockAction2 = new MockMenuAction();
		MockMenuAction mockAction3 = new MockMenuAction();
		MockMenuAction mockAction4 = new MockMenuAction();
		MockMenuAction mockAction5 = new MockMenuAction();
		List<MenuAction> expectedMenuActions = pencilWriterActionMenu.getMenuActions();

		expectedMenuActions.clear();
		expectedMenuActions.add(mockAction1);
		expectedMenuActions.add(mockAction2);
		expectedMenuActions.add(mockAction3);
		expectedMenuActions.add(mockAction4);
		expectedMenuActions.add(mockAction5);

		int userSelection = 3;
		checkPerformWasCalledOnlyOnActionIndicatedByUser(pencilWriterActionMenu,
				expectedMenuActions, userSelection);
	}

	@Test
	public void testOpenMainMenu_performsPencilActionBasedOnUserChoice_lastAction()
			throws Exception {
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				new MockPencilWriterActionMenuDisplayHelper());

		MockMenuAction mockAction1 = new MockMenuAction();
		MockMenuAction mockAction2 = new MockMenuAction();
		MockMenuAction mockAction3 = new MockMenuAction();
		MockMenuAction mockAction4 = new MockMenuAction();
		List<MenuAction> expectedMenuActions = pencilWriterActionMenu.getMenuActions();

		expectedMenuActions.clear();
		expectedMenuActions.add(mockAction1);
		expectedMenuActions.add(mockAction2);
		expectedMenuActions.add(mockAction3);
		expectedMenuActions.add(mockAction4);

		int userSelection = 4;
		checkPerformWasCalledOnlyOnActionIndicatedByUser(pencilWriterActionMenu,
				expectedMenuActions, userSelection);
	}

	private void checkPerformWasCalledOnlyOnActionIndicatedByUser(
			PencilWriterActionMenu pencilWriterActionMenu, List<MenuAction> expectedMenuActions,
			int userSelection) {

		assertFalse(this.mockInputScanner.nextLineWasCalled());

		this.mockInputScanner.setTextReturnedFromNextLine("" + userSelection + "");
		pencilWriterActionMenu.openMainMenuWithQuitOption();

		assertTrue(this.mockInputScanner.nextLineWasCalled());

		int indexOfMenuActionToCheck = userSelection - 1;
		MockMenuAction menuActionToCheck = (MockMenuAction) expectedMenuActions
				.get(indexOfMenuActionToCheck);
		assertTrue(menuActionToCheck.performWasCalled());

		for (int indexOfMenuAction = 0; indexOfMenuAction < expectedMenuActions
				.size(); indexOfMenuAction++) {
			MockMenuAction menuActionThatShouldNotHaveBeenCalled = (MockMenuAction) expectedMenuActions
					.get(indexOfMenuAction);
			if (indexOfMenuAction != indexOfMenuActionToCheck) {
				assertFalse(menuActionThatShouldNotHaveBeenCalled.performWasCalled());
			}
		}
	}

}
