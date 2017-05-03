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

public class WritePencilActionTest {

	private Field inputScannerWrapperSingletonPrivateField;
	private MockInputScannerWrapperSingleton mockScannerWrapper;
	private InputStream standardIn;

	@Before
	public void setUp() throws Exception {
		this.standardIn = System.in;

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
	}

	@Test
	public void testPerformMakesPencilWriteInputText() throws Exception {
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
