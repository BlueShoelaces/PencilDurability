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

public class WritePencilActionTest extends TestHelper {

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
	public void testGetters() throws Exception {
		MockPencil mockPencil = new MockPencil();
		MockPaper mockPaper = new MockPaper();
		WritePencilAction writePencilAction = new WritePencilAction(mockPencil, mockPaper);

		assertSame(mockPencil, writePencilAction.getPencil());
		assertSame(mockPaper, writePencilAction.getPaper());
	}

	@Test
	public void testImplementsInterface() throws Exception {
		assertImplementsInterface(WritePencilAction.class, PencilAction.class);
	}

	@Test
	public void testPerform_PromptsUserForTextToWrite() throws Exception {
		WritePencilAction writePencilAction = new WritePencilAction(new MockPencil(),
				new MockPaper());
		writePencilAction.perform();

		String actualOutputText = this.mockOutputStream.toString();
		assertEquals("Enter some text to write: ", actualOutputText);
	}

	@Test
	public void testPerform_MakesPencilWriteInputText() throws Exception {
		MockPencil mockPencil = new MockPencil();
		MockPaper mockPaper = new MockPaper();

		String expectedTextWrittenToPaper = "To obtain, something of equal value must be lost.";
		this.mockScannerWrapper.setTextReturnedFromNextLine(expectedTextWrittenToPaper);

		WritePencilAction writePencilAction = new WritePencilAction(mockPencil, mockPaper);
		writePencilAction.perform();

		assertTrue(this.mockScannerWrapper.nextLineWasCalled());

		PaperInterface actualPaperWrittenTo = mockPencil.getPaperWrittenTo();
		assertSame(mockPaper, actualPaperWrittenTo);

		String actualTextWrittenToPaper = mockPencil.getTextWrittenToPaper();
		assertEquals(expectedTextWrittenToPaper, actualTextWrittenToPaper);
	}
}
