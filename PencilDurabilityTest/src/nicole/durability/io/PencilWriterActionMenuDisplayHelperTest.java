package nicole.durability.io;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;

import org.junit.*;

import nicole.durability.actions.*;
import nicole.durability.mocks.*;
import nicole.test.*;

public class PencilWriterActionMenuDisplayHelperTest extends TestHelper {

	private PrintStream standardOut;
	private ByteArrayOutputStream mockOutputStream;

	@Before
	public void setUp() throws Exception {
		this.standardOut = System.out;
		this.mockOutputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(this.mockOutputStream));
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(this.standardOut);
	}

	@Test
	public void testImplementsInterface() throws Exception {
		assertImplementsInterface(PencilWriterActionMenuDisplayHelper.class,
				DisplayHelperInterface.class);
	}

	@Test
	public void testDisplayMainMenuWithUserPrompt() throws Exception {
		MockMenuAction firstMockPencilAction = new MockMenuAction();
		MockMenuAction secondMockPencilAction = new MockMenuAction();
		MockMenuAction thirdMockPencilAction = new MockMenuAction();

		String expectedFirstActionText = "here's the first action";
		String expectedSecondActionText = "here's the second action";
		String expectedThirdActionText = "here's the third action";
		firstMockPencilAction.setTextToDisplay(expectedFirstActionText);
		secondMockPencilAction.setTextToDisplay(expectedSecondActionText);
		thirdMockPencilAction.setTextToDisplay(expectedThirdActionText);

		List<MenuAction> pencilActionsToDisplay = Arrays.asList(firstMockPencilAction,
				secondMockPencilAction, thirdMockPencilAction);

		DisplayHelperInterface displayHelper = new PencilWriterActionMenuDisplayHelper();
		displayHelper.displayMainMenuWithUserPrompt(pencilActionsToDisplay);

		String mainMenuTitle = "\nMAIN MENU:\n";
		String expectedFirstMenuItemText = " 1  " + expectedFirstActionText + "\n";
		String expectedSecondMenuItemText = " 2  " + expectedSecondActionText + "\n";
		String expectedThirdMenuItemText = " 3  " + expectedThirdActionText + "\n";
		String userPrompt = "Select a number (or 0 to quit) and hit [ENTER]\n";
		String expectedTextToDisplay = mainMenuTitle + expectedFirstMenuItemText
				+ expectedSecondMenuItemText + expectedThirdMenuItemText + userPrompt;

		String actualTextToDisplay = this.mockOutputStream.toString();

		assertEquals(expectedTextToDisplay, actualTextToDisplay);
	}

	@Test
	public void testDisplayPencilStats() throws Exception {
		DisplayHelperInterface displayHelper = new PencilWriterActionMenuDisplayHelper();

		MockPencil mockPencil = new MockPencil();

		int pencilLength = 4;
		int pencilDurability = 75;
		int eraserDurability = 825;

		mockPencil.setCurrentLength(pencilLength);
		mockPencil.setCurrentDurability(pencilDurability);
		mockPencil.setEraserDurability(eraserDurability);

		displayHelper.displayPencilStats(mockPencil);

		String expectedStatsDisplayed = createPencilStatsDisplay(pencilLength, pencilDurability,
				eraserDurability);
		String actualStatsDisplayed = this.mockOutputStream.toString();

		assertEquals(expectedStatsDisplayed, actualStatsDisplayed);
	}

	private String createPencilStatsDisplay(int pencilLength, int pencilDurability,
			int eraserDurability) {
		String expectedLengthText = "  Pencil length = " + pencilLength + "\n";
		String expectedPencilDurabilityText = "  Durability = " + pencilDurability + "\n";
		String expectedEraserDurabilityText = "  Eraser durability = " + eraserDurability + "\n";

		String expectedStatsDisplayed = "\nYOUR PENCIL:\n" + expectedLengthText
				+ expectedPencilDurabilityText + expectedEraserDurabilityText;
		return expectedStatsDisplayed;
	}
}
