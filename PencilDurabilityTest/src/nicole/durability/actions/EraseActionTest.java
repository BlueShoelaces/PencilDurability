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

public class EraseActionTest extends TestHelper {

	private InputStream standardIn;
	private PrintStream standardOut;
	private ByteArrayOutputStream mockOutputStream;
	private Field inputScannerWrapperSingletonPrivateField;
	private MockInputScannerWrapperSingleton mockScannerWrapper;

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
		System.setIn(this.standardIn);
		System.setOut(this.standardOut);
		this.inputScannerWrapperSingletonPrivateField.set(null, null);
	}

	@Test
	public void testImplementsInterface() throws Exception {
		assertImplementsInterface(EraseAction.class, MenuAction.class);
	}

	@Test
	public void testMenuTextToDisplay() throws Exception {
		EraseAction eraseAction = new EraseAction(new MockPencil(), new MockPaper());
		String expectedMenuText = "Erase some text";
		String actualMenuText = eraseAction.getMenuTextToDisplay();
		assertEquals(expectedMenuText, actualMenuText);
	}

	@Test
	public void testPerform_promptsUserForTextToErase() throws Exception {
		EraseAction eraseAction = new EraseAction(new MockPencil(), new MockPaper());
		eraseAction.perform();

		String actualOutputText = this.mockOutputStream.toString();
		assertEquals("Enter some text to erase: ", actualOutputText);
	}

	@Test
	public void testPerform_makesPencilEraseInputTextFromPaper() throws Exception {
		MockPencil mockPencil = new MockPencil();
		MockPaper expectedPaperErasedFrom = new MockPaper();
		String expectedTextErased = "Erase me, Seymour!";
		this.mockScannerWrapper.setTextReturnedFromNextLine(expectedTextErased);

		EraseAction eraseAction = new EraseAction(mockPencil, expectedPaperErasedFrom);
		eraseAction.perform();

		assertTrue(this.mockScannerWrapper.nextLineWasCalled());

		PaperInterface actualPaperErasedFrom = mockPencil.getPaperErasedFrom();
		assertSame(expectedPaperErasedFrom, actualPaperErasedFrom);

		String actualTextErased = mockPencil.getTextErasedFromPaper();
		assertEquals(expectedTextErased, actualTextErased);
	}

	@Test
	public void testGetters() throws Exception {
		MockPencil expectedPencil = new MockPencil();
		MockPaper expectedPaper = new MockPaper();
		EraseAction eraseAction = new EraseAction(expectedPencil, expectedPaper);

		assertSame(expectedPencil, eraseAction.getPencil());
		assertSame(expectedPaper, eraseAction.getPaper());
	}
}
