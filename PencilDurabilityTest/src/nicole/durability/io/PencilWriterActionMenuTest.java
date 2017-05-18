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

	private static final int EXPECTED_NUMBER_OF_MENU_ITEMS = 2;

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
		assertSame(expectedDisplayHelper, pencilWriterActionMenu.getDisplayHelper());

		List<PencilAction> actualMenuActions = pencilWriterActionMenu.getMenuActions();
		assertEquals(EXPECTED_NUMBER_OF_MENU_ITEMS, actualMenuActions.size());

		int expectedWritePencilIndex = 0;
		checkWritePencilAction(actualMenuActions, expectedWritePencilIndex);

		int expectedShowPaperIndex = 1;
		checkShowPaperAction(actualMenuActions, expectedShowPaperIndex);
	}

	private void checkShowPaperAction(List<PencilAction> actualMenuActions,
			int expectedShowPaperIndex) {
		PencilAction expectedShowPaperAction = actualMenuActions.get(expectedShowPaperIndex);
		assertIsOfTypeAndGet(ShowPaperAction.class, expectedShowPaperAction);
	}

	private void checkWritePencilAction(List<PencilAction> actualMenuActions,
			int expectedWritePencilIndex) {
		PencilAction expectedWritePencilAction = actualMenuActions.get(expectedWritePencilIndex);
		WritePencilAction actualWritePencilAction = assertIsOfTypeAndGet(WritePencilAction.class,
				expectedWritePencilAction);

		Pencil actualPencil = assertIsOfTypeAndGet(Pencil.class, actualWritePencilAction.getPencil());

		int actualDurability = actualPencil.getCurrentDurability();
		int expectedDurability = 500;
		assertEquals(expectedDurability, actualDurability);

		int actualPencilLength = actualPencil.getPencilLength();
		int expectedPencilLength = 4;
		assertEquals(expectedPencilLength, actualPencilLength);

		assertIsOfTypeAndGet(Paper.class, actualWritePencilAction.getPaper());
	}

	@Test
	public void testOpenMainMenu_passesPencilActionsToDisplayHelper() throws Exception {
		MockPencilWriterActionMenuDisplayHelper mockDisplayHelper = new MockPencilWriterActionMenuDisplayHelper();
		PencilWriterActionMenuInterface pencilWriterActionMenu = new PencilWriterActionMenu(
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
	public void testOpenMainMenu_takesNoActionIfInputIsNotANumber() throws Exception {
		PencilWriterActionMenu pencilWriterActionMenu = new PencilWriterActionMenu(
				new MockPencilWriterActionMenuDisplayHelper());

		List<PencilAction> expectedPencilActions = pencilWriterActionMenu.getMenuActions();
		expectedPencilActions.clear();
		MockPencilAction theOnlyPencilAction = new MockPencilAction();
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

		List<PencilAction> expectedPencilActions = pencilWriterActionMenu.getMenuActions();
		expectedPencilActions.clear();
		MockPencilAction theOnlyPencilAction = new MockPencilAction();
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

		List<PencilAction> expectedPencilActions = pencilWriterActionMenu.getMenuActions();
		expectedPencilActions.clear();
		MockPencilAction theOnlyPencilAction = new MockPencilAction();
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

		MockPencilAction mockAction1 = new MockPencilAction();
		MockPencilAction mockAction2 = new MockPencilAction();
		MockPencilAction mockAction3 = new MockPencilAction();
		List<PencilAction> expectedPencilActions = pencilWriterActionMenu.getMenuActions();

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

		MockPencilAction mockAction1 = new MockPencilAction();
		MockPencilAction mockAction2 = new MockPencilAction();
		MockPencilAction mockAction3 = new MockPencilAction();
		List<PencilAction> expectedPencilActions = pencilWriterActionMenu.getMenuActions();

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

		MockPencilAction mockAction1 = new MockPencilAction();
		MockPencilAction mockAction2 = new MockPencilAction();
		MockPencilAction mockAction3 = new MockPencilAction();
		List<PencilAction> expectedPencilActions = pencilWriterActionMenu.getMenuActions();

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

//	@Test
//	public void testOpenMainMenu_performsFirstPencilAction() throws Exception {
//		PencilWriterActionMenuInterface pencilWriterActionMenu = new PencilWriterActionMenu(
//				new MockPencilWriterActionMenuDisplayHelper());
//
//		List<PencilAction> menuActions = pencilWriterActionMenu.getMenuActions();
//		menuActions.clear();
//		MockPencilAction mockPencilAction = new MockPencilAction();
//		menuActions.add(mockPencilAction);
//
//		pencilWriterActionMenu.openMainMenu();
//
//		boolean performWasCalled = mockPencilAction.performWasCalled();
//		assertTrue(performWasCalled);
//	}
}
