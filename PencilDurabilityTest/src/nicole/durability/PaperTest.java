package nicole.durability;

import static org.junit.Assert.assertEquals;

import java.io.*;

import org.junit.*;

import nicole.test.*;

public class PaperTest extends TestHelper {

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
		assertImplementsInterface(Paper.class, PaperInterface.class);
	}

	@Test
	public void testPaperCanBeWrittenOn() throws Exception {
		PaperInterface paper = new Paper();
		String expectedStringWrittenToPaper = "To be or not to be?";
		paper.write(expectedStringWrittenToPaper);

		assertEquals("", this.mockOutputStream.toString());

		paper.showPaper();

		assertEquals(expectedStringWrittenToPaper, this.mockOutputStream.toString());
	}

	@Test
	public void testShowPaper_nothingWrittenYet() throws Exception {
		PaperInterface paper = new Paper();
		String expectedTextWritten = "";
		paper.showPaper();

		assertEquals(expectedTextWritten, this.mockOutputStream.toString());
	}

	@Test
	public void testWritingOnPaperAppendsNewText() throws Exception {
		PaperInterface paper = new Paper();

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

	@Test
	public void testReplaceWithWhitespace() throws Exception {
		Paper paper = new Paper();
		String textAlreadyOnPaper = "Je ne veux pas travailler... je ne veux pas dejeuner... je veux seulement oublier... et puis, je fume.";
		paper.write(textAlreadyOnPaper);
		assertEquals(textAlreadyOnPaper, paper.getTextOnPaper());

		paper.replaceWithWhitespace("je");
		String expectedTextAfterErasing = "Je ne veux pas travailler... je ne veux pas dejeuner... je veux seulement oublier... et puis,    fume.";

		assertEquals(expectedTextAfterErasing, paper.getTextOnPaper());
	}
}
