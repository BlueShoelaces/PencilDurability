package nicole.durability.actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.lang.reflect.*;

import org.junit.*;

import nicole.durability.*;
import nicole.durability.io.*;
import nicole.durability.mocks.*;
import nicole.test.*;

public class EditActionTest extends TestHelper {

	private Field inputScannerWrapperSingletonPrivateField;
	private MockInputScannerWrapperSingleton mockScannerWrapper;
	private InputStream standardIn;
	private PrintStream standardOut;
	private ByteArrayOutputStream mockOutputStream;

	@Before
	public void setUp() throws Exception {
		this.standardIn = System.in;
		this.standardOut = System.out;
		this.mockOutputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(this.mockOutputStream));

		this.inputScannerWrapperSingletonPrivateField = InputScannerWrapperSingleton.class
				.getDeclaredField("instance");
		this.inputScannerWrapperSingletonPrivateField.setAccessible(true);

		this.mockScannerWrapper = new MockInputScannerWrapperSingleton();
		this.inputScannerWrapperSingletonPrivateField.set(null, this.mockScannerWrapper);
	}

	@After
	public void tearDown() throws Exception {
		this.inputScannerWrapperSingletonPrivateField.set(null, null);
		System.setIn(this.standardIn);
		System.setOut(this.standardOut);
	}

	@Test
	public void testImplementsMenuAction() throws Exception {
		assertImplementsInterface(EditAction.class, MenuAction.class);
	}

	@Test
	public void testGetters() throws Exception {
		PencilInterface mockPencil = new MockPencil();
		PaperInterface mockPaper = new MockPaper();
		EditAction editAction = new EditAction(mockPencil, mockPaper);

		assertSame(mockPencil, editAction.getPencil());
		assertSame(mockPaper, editAction.getPaper());
	}

	@Test
	public void testGetMenuTextToDisplay() throws Exception {
		EditAction editAction = new EditAction(new MockPencil(), new MockPaper());
		String actualMenuTextToDisplay = editAction.getMenuTextToDisplay();
		String expectedMenuTextToDisplay = "Write over previously erased text";

		assertEquals(expectedMenuTextToDisplay, actualMenuTextToDisplay);
	}

	@Test
	public void testPerform_PromptsUserForTextToWrite() throws Exception {
		EditAction editAction = new EditAction(new MockPencil(), new MockPaper());
		editAction.perform();

		String actualOutputText = this.mockOutputStream.toString();
		assertEquals("Enter some text to write: ", actualOutputText);
	}

	@Test
	public void testPerform_MakesPencilWriteInputText() throws Exception {
		MockPencil mockPencil = new MockPencil();
		MockPaper mockPaper = new MockPaper();

		String expectedTextWrittenToPaper = "Aw, jeez, Rick...";
		this.mockScannerWrapper.setTextReturnedFromNextLine(expectedTextWrittenToPaper);

		EditAction editAction = new EditAction(mockPencil, mockPaper);
		editAction.perform();

		assertTrue(this.mockScannerWrapper.nextLineWasCalled());

		PaperInterface actualPaperWrittenTo = mockPencil.getPaperWrittenToInWhitespaceGap();
		assertSame(mockPaper, actualPaperWrittenTo);

		String actualTextWrittenToPaper = mockPencil.getTextWrittenToPaperInWhitespaceGap();
		assertEquals(expectedTextWrittenToPaper, actualTextWrittenToPaper);
	}
}
