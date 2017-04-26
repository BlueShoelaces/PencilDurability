package nicole.durability;

import static org.junit.Assert.assertEquals;

import java.io.*;

import org.junit.*;

public class PaperTest {

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
	public void testPaperCanBeWrittenOn() throws Exception {
		Paper paper = new Paper();
		String expectedStringWrittenToPaper = "To be or not to be?";
		paper.write(expectedStringWrittenToPaper);

		assertEquals("", this.mockOutputStream.toString());

		paper.showPaper();

		assertEquals(expectedStringWrittenToPaper, this.mockOutputStream.toString());
	}

	@Test
	public void testShowPaper_nothingWrittenYet() throws Exception {
		Paper paper = new Paper();
		String expectedTextWritten = "";
		paper.showPaper();

		assertEquals(expectedTextWritten, this.mockOutputStream.toString());
	}

	@Test
	public void testWritingOnPaperAppendsNewText() throws Exception {
		Paper paper = new Paper();

		String firstTextWritten = "It is a truth universally acknowledged";
		String secondTextWritten = " that a single man in possession of a good fortune";
		String thirdTextWritten = " must be in want of a wife.";

		paper.write(firstTextWritten);
		paper.write(secondTextWritten);
		paper.write(thirdTextWritten);
		paper.showPaper();

		assertEquals(firstTextWritten + secondTextWritten + thirdTextWritten,
				this.mockOutputStream.toString());
	}
}
