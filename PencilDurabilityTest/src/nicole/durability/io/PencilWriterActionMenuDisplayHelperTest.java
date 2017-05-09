package nicole.durability.io;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;

import org.junit.*;

import nicole.durability.actions.*;
import nicole.durability.mocks.*;

public class PencilWriterActionMenuDisplayHelperTest {

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
	public void testDisplay() throws Exception {
		MockPencilAction firstMockPencilAction = new MockPencilAction();
		MockPencilAction secondMockPencilAction = new MockPencilAction();
		MockPencilAction thirdMockPencilAction = new MockPencilAction();

		String expectedFirstActionText = "here's the first action";
		String expectedSecondActionText = "here's the second action";
		String expectedThirdActionText = "here's the third action";
		firstMockPencilAction.setTextToDisplay(expectedFirstActionText);
		secondMockPencilAction.setTextToDisplay(expectedSecondActionText);
		thirdMockPencilAction.setTextToDisplay(expectedThirdActionText);

		List<PencilAction> pencilActionsToDisplay = Arrays.asList(firstMockPencilAction,
				secondMockPencilAction, thirdMockPencilAction);

		PencilWriterActionMenuDisplayHelper displayHelper = new PencilWriterActionMenuDisplayHelper();
		displayHelper.display(pencilActionsToDisplay);

		String expectedTextToDisplay = expectedFirstActionText + "\n" + expectedSecondActionText
				+ "\n" + expectedThirdActionText + "\n";

		String actualTextToDisplay = this.mockOutputStream.toString();

		assertEquals(expectedTextToDisplay, actualTextToDisplay);
	}
}
