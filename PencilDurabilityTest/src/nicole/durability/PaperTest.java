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
		String visibleTextWritten = "To be or not to be?";
		String expectedStringWrittenToPaper = visibleTextWritten + "\n";
		paper.write(visibleTextWritten);

		assertEquals("", this.mockOutputStream.toString());

		paper.showPaper();

		checkShowPaperAppendsNewLinesToStartAndEnd(expectedStringWrittenToPaper);
	}

	@Test
	public void testWriteInWhitespaceGap() throws Exception {
		PaperInterface paper = new Paper();
		paper.write("It was       at the beach. I played in the      and in the      .");

		paper.writeInWhitespaceGap("sunny");
		paper.writeInWhitespaceGap("sand");
		paper.writeInWhitespaceGap("waves");
		paper.writeInWhitespaceGap("don't write me!");

		String expectedTextOnPaperAfterWritingInWhitespaceGaps = "It was sunny at the beach. I played in the sand and in the waves.\n";

		assertEquals(expectedTextOnPaperAfterWritingInWhitespaceGaps, paper.getTextOnPaper());
	}

	@Test
	public void testShowPaper_nothingWrittenYet() throws Exception {
		PaperInterface paper = new Paper();
		String expectedTextWritten = "";
		paper.showPaper();

		checkShowPaperAppendsNewLinesToStartAndEnd(expectedTextWritten);
	}

	@Test
	public void testWritingOnPaperAppendsNewText_withNewLinesInBetween() throws Exception {
		PaperInterface paper = new Paper();

		String firstTextWritten = "It is a truth universally acknowledged";
		String secondTextWritten = " that a single man in possession of a good fortune";
		String thirdTextWritten = " must be in want of a wife.";

		paper.write(firstTextWritten);
		paper.write(secondTextWritten);
		paper.write(thirdTextWritten);
		paper.showPaper();

		String expectedTextWrittenToPaper = firstTextWritten + "\n" + secondTextWritten + "\n"
				+ thirdTextWritten + "\n";

		checkShowPaperAppendsNewLinesToStartAndEnd(expectedTextWrittenToPaper);
	}

	@Test
	public void testReplaceWithWhitespace() throws Exception {
		Paper paper = new Paper();
		String textAlreadyOnPaper = "Je ne veux pas travailler... je ne veux pas dejeuner... je veux seulement oublier... et puis, je fume.";
		paper.write(textAlreadyOnPaper);
		String textAlreadyOnPaperWithTrailingWhitespace = textAlreadyOnPaper + "\n";
		assertEquals(textAlreadyOnPaperWithTrailingWhitespace, paper.getTextOnPaper());

		paper.replaceWithWhitespace("je", 10);
		String expectedTextAfterErasing = "Je ne veux pas travailler... je ne veux pas dejeuner... je veux seulement oublier... et puis,    fume.\n";

		assertEquals(expectedTextAfterErasing, paper.getTextOnPaper());
	}

	@Test
	public void testReplaceWithWhitespace_ErasesCharactersInReverseOrderUntilEraserDurabilityRunsOut()
			throws Exception {
		Paper paper = new Paper();
		String textAlreadyOnPaper = "That's why the people of this world believe in Garnet, Amethyst, and Pearl... and Steven!";
		paper.write(textAlreadyOnPaper);

		int eraserDurability = 7;
		paper.replaceWithWhitespace("Amethyst, and", eraserDurability);

		String expectedTextAfterErasing = "That's why the people of this world believe in Garnet, Ameth         Pearl... and Steven!\n";

		assertEquals(expectedTextAfterErasing, paper.getTextOnPaper());
	}

	private void checkShowPaperAppendsNewLinesToStartAndEnd(String expectedStringWrittenToPaper) {
		String textWithNewLinesIncludedIncluded = "\n" + expectedStringWrittenToPaper + "\n";
		assertEquals(textWithNewLinesIncludedIncluded, this.mockOutputStream.toString());
	}
}
