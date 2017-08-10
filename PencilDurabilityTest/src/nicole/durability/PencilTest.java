package nicole.durability;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import nicole.durability.mocks.*;
import nicole.test.*;

public class PencilTest extends TestHelper {

	private static final int PENCIL_LENGTH_0 = 0;
	private static final int PENCIL_LENGTH_5 = 5;
	private static final int PENCIL_DURABILITY_500 = 500;
	private static int ERASER_DURABILITY_25 = 25;

	@Test
	public void testImplementsInterface() throws Exception {
		assertImplementsInterface(Pencil.class, PencilInterface.class);
	}

	@Test
	public void testPencilConstructor() {
		int expectedPencilDurability = 1500;
		int expectedPencilLength = PENCIL_LENGTH_5;
		int expectedEraserDurability = 10;

		PencilInterface pencil = new Pencil(expectedPencilDurability, expectedPencilLength,
				expectedEraserDurability);
		int actualPencilDurability = pencil.getCurrentPencilDurability();
		int actualPencilLength = pencil.getPencilLength();
		int actualEraserDurability = pencil.getEraserDurability();

		assertEquals(expectedPencilDurability, actualPencilDurability);
		assertEquals(expectedPencilLength, actualPencilLength);
		assertEquals(expectedEraserDurability, actualEraserDurability);
	}

	@Test
	public void testWriteOnPaper() throws Exception {
		String expectedTextWrittenToPaper = "What a long, strange trip it's been.";
		int durability = expectedTextWrittenToPaper.length() * 2;

		PencilInterface pencil = new Pencil(durability, PENCIL_LENGTH_5, ERASER_DURABILITY_25);
		MockPaper mockPaper = new MockPaper();

		pencil.writeOnPaper(mockPaper, expectedTextWrittenToPaper);

		String actualTextWrittenToPaper = mockPaper.getTextPassedToWrite();
		assertEquals(expectedTextWrittenToPaper, actualTextWrittenToPaper);
	}

	@Test
	public void testWriteOnPaper_PencilPointDegradesWithUse_lowerCaseExpendsOneGraphitePoint()
			throws Exception {
		int startingDurability = 1500;
		PencilInterface pencil = new Pencil(startingDurability, PENCIL_LENGTH_5,
				ERASER_DURABILITY_25);
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		pencil.writeOnPaper(new MockPaper(), lowerCaseLetters);

		int expectedDurabilityAfterWriting = startingDurability - 26;
		int actualDurabilityAfterWriting = pencil.getCurrentPencilDurability();

		assertEquals(expectedDurabilityAfterWriting, actualDurabilityAfterWriting);
	}

	@Test
	public void testWriteOnPaper_PencilPointDegradesWithUse_punctuationAndNumbersExpendOneGraphitePoint()
			throws Exception {
		int startingDurability = 1500;
		PencilInterface pencil = new Pencil(startingDurability, PENCIL_LENGTH_5,
				ERASER_DURABILITY_25);
		String punctuation = ".?!,;:-()[]{}'\"0123456789";
		pencil.writeOnPaper(new MockPaper(), punctuation);

		int expectedDurabilityAfterWriting = startingDurability - 25;
		int actualDurabilityAfterWriting = pencil.getCurrentPencilDurability();

		assertEquals(expectedDurabilityAfterWriting, actualDurabilityAfterWriting);
	}

	@Test
	public void testWriteOnPaper_PencilPointDegradesWithUse_upperCaseExpendsTwoGraphitePoints()
			throws Exception {
		int startingDurability = 1500;
		PencilInterface pencil = new Pencil(startingDurability, PENCIL_LENGTH_5,
				ERASER_DURABILITY_25);
		String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		pencil.writeOnPaper(new MockPaper(), upperCaseLetters);

		int expectedDurabilityAfterWriting = startingDurability - (2 * 26);
		int actualDurabilityAfterWriting = pencil.getCurrentPencilDurability();

		assertEquals(expectedDurabilityAfterWriting, actualDurabilityAfterWriting);
	}

	@Test
	public void testWriteOnPaper_PencilPointDegradesWithUse_whitespaceExpendsNoGraphite()
			throws Exception {
		int startingDurability = 1500;
		PencilInterface pencil = new Pencil(startingDurability, PENCIL_LENGTH_5,
				ERASER_DURABILITY_25);
		String whitespace = "\t   \n";
		pencil.writeOnPaper(new MockPaper(), whitespace);

		int expectedDurabilityAfterWriting = startingDurability;
		int actualDurabilityAfterWriting = pencil.getCurrentPencilDurability();

		assertEquals(expectedDurabilityAfterWriting, actualDurabilityAfterWriting);
	}

	@Test
	public void testWriteOnPaper_PencilPointDegradesWithUse_CapitalLetterIsNotWrittenIfOnlyOneGraphitePointRemains()
			throws Exception {
		Pencil pencil = new Pencil(5, PENCIL_LENGTH_5, ERASER_DURABILITY_25);

		MockPaper mockPaper = new MockPaper();
		pencil.writeOnPaper(mockPaper, "abcdE");

		String actualTextWritten = mockPaper.getTextPassedToWrite();
		String expectedTextWritten = "abcd ";
		assertEquals(expectedTextWritten, actualTextWritten);
		assertEquals(1, pencil.getCurrentPencilDurability());
	}

	@Test
	public void testWriteOnPaper_DullPencilWritesOnlyBlankSpaces() throws Exception {
		int durability = 0;
		PencilInterface pencil = new Pencil(durability, PENCIL_LENGTH_5, ERASER_DURABILITY_25);
		MockPaper mockPaper = new MockPaper();

		String textPencilIsDirectedToWrite = "I should sharpen my pencil...";
		pencil.writeOnPaper(mockPaper, textPencilIsDirectedToWrite);

		String expectedTextWritten = "                             ";
		String actualTextWritten = mockPaper.getTextPassedToWrite();

		assertEquals(expectedTextWritten, actualTextWritten);
	}

	@Test
	public void testWriteOnPaperInWhitespaceGap() throws Exception {
		String expectedTextWrittenToPaper = "What a long, strange trip it's been.";
		int durability = expectedTextWrittenToPaper.length() * 2;

		PencilInterface pencil = new Pencil(durability, PENCIL_LENGTH_5, ERASER_DURABILITY_25);
		MockPaper mockPaper = new MockPaper();

		pencil.writeOnPaperInWhitespaceGap(mockPaper, expectedTextWrittenToPaper);

		String actualTextWrittenToPaper = mockPaper.getTextPassedToWriteInWhitespaceGap();
		assertEquals(expectedTextWrittenToPaper, actualTextWrittenToPaper);
	}

	@Test
	public void testWriteOnPaperInWhitespaceGap_PencilPointDegradesWithUse_lowerCaseExpendsOneGraphitePoint()
			throws Exception {
		int startingDurability = 1500;
		PencilInterface pencil = new Pencil(startingDurability, PENCIL_LENGTH_5,
				ERASER_DURABILITY_25);
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		pencil.writeOnPaperInWhitespaceGap(new MockPaper(), lowerCaseLetters);

		int expectedDurabilityAfterWriting = startingDurability - 26;
		int actualDurabilityAfterWriting = pencil.getCurrentPencilDurability();

		assertEquals(expectedDurabilityAfterWriting, actualDurabilityAfterWriting);
	}

	@Test
	public void testWriteOnPaperInWhitespaceGap_PencilPointDegradesWithUse_punctuationAndNumbersExpendOneGraphitePoint()
			throws Exception {
		int startingDurability = 1500;
		PencilInterface pencil = new Pencil(startingDurability, PENCIL_LENGTH_5,
				ERASER_DURABILITY_25);
		String punctuation = ".?!,;:-()[]{}'\"0123456789";
		pencil.writeOnPaperInWhitespaceGap(new MockPaper(), punctuation);

		int expectedDurabilityAfterWriting = startingDurability - 25;
		int actualDurabilityAfterWriting = pencil.getCurrentPencilDurability();

		assertEquals(expectedDurabilityAfterWriting, actualDurabilityAfterWriting);
	}

	@Test
	public void testWriteOnPaperInWhitespaceGap_PencilPointDegradesWithUse_upperCaseExpendsTwoGraphitePoints()
			throws Exception {
		int startingDurability = 1500;
		PencilInterface pencil = new Pencil(startingDurability, PENCIL_LENGTH_5,
				ERASER_DURABILITY_25);
		String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		pencil.writeOnPaperInWhitespaceGap(new MockPaper(), upperCaseLetters);

		int expectedDurabilityAfterWriting = startingDurability - (2 * 26);
		int actualDurabilityAfterWriting = pencil.getCurrentPencilDurability();

		assertEquals(expectedDurabilityAfterWriting, actualDurabilityAfterWriting);
	}

	@Test
	public void testWriteOnPaperInWhitespaceGap_PencilPointDegradesWithUse_whitespaceExpendsNoGraphite()
			throws Exception {
		int startingDurability = 1500;
		PencilInterface pencil = new Pencil(startingDurability, PENCIL_LENGTH_5,
				ERASER_DURABILITY_25);
		String whitespace = "\t   \n";
		pencil.writeOnPaperInWhitespaceGap(new MockPaper(), whitespace);

		int expectedDurabilityAfterWriting = startingDurability;
		int actualDurabilityAfterWriting = pencil.getCurrentPencilDurability();

		assertEquals(expectedDurabilityAfterWriting, actualDurabilityAfterWriting);
	}

	@Test
	public void testWriteOnPaperInWhitespaceGap_PencilPointDegradesWithUse_CapitalLetterIsNotWrittenIfOnlyOneGraphitePointRemains()
			throws Exception {
		PencilInterface pencil = new Pencil(5, PENCIL_LENGTH_5, ERASER_DURABILITY_25);

		MockPaper mockPaper = new MockPaper();
		pencil.writeOnPaperInWhitespaceGap(mockPaper, "abcdE");

		String actualTextWritten = mockPaper.getTextPassedToWriteInWhitespaceGap();
		String expectedTextWritten = "abcd ";
		assertEquals(expectedTextWritten, actualTextWritten);
		assertEquals(1, pencil.getCurrentPencilDurability());
	}

	@Test
	public void testWriteOnPaperInWhitespaceGap_DullPencilWritesOnlyBlankSpaces() throws Exception {
		int durability = 0;
		PencilInterface pencil = new Pencil(durability, PENCIL_LENGTH_5, ERASER_DURABILITY_25);
		MockPaper mockPaper = new MockPaper();

		String textPencilIsDirectedToWrite = "I should sharpen my pencil...";
		pencil.writeOnPaperInWhitespaceGap(mockPaper, textPencilIsDirectedToWrite);

		String expectedTextWritten = "                             ";
		String actualTextWritten = mockPaper.getTextPassedToWriteInWhitespaceGap();

		assertEquals(expectedTextWritten, actualTextWritten);
	}

	@Test
	public void testSharpeningPencilRestoresOriginalDurability() throws Exception {

		PaperInterface mockPaper = new MockPaper();
		int originalDurability1 = 500;
		PencilInterface pencil1 = new Pencil(originalDurability1, PENCIL_LENGTH_5,
				ERASER_DURABILITY_25);

		String textToWrite = "BILL: Pai Mei taught you the Five Point Palm Exploding Heart Technique?\n";
		pencil1.writeOnPaper(mockPaper, textToWrite);

		assertTrue(pencil1.getCurrentPencilDurability() < originalDurability1);

		pencil1.sharpen();

		assertEquals(pencil1.getCurrentPencilDurability(), originalDurability1);

		int originalDurability2 = 50;
		PencilInterface pencil2 = new Pencil(originalDurability2, PENCIL_LENGTH_5,
				ERASER_DURABILITY_25);
		pencil2.writeOnPaper(mockPaper, "BRIDE: Course he did.\n");

		assertTrue(pencil2.getCurrentPencilDurability() < originalDurability2);

		pencil2.sharpen();

		assertEquals(pencil2.getCurrentPencilDurability(), originalDurability2);
	}

	@Test
	public void testSharpeningPencilMakesItShorter() throws Exception {
		int originalPencilLength = PENCIL_LENGTH_5;
		PencilInterface pencil = new Pencil(PENCIL_DURABILITY_500, originalPencilLength,
				ERASER_DURABILITY_25);

		int expectedPencilLengthAfterSharpening = originalPencilLength - 1;
		pencil.sharpen();

		assertEquals(expectedPencilLengthAfterSharpening, pencil.getPencilLength());

		expectedPencilLengthAfterSharpening = expectedPencilLengthAfterSharpening - 1;
		pencil.sharpen();

		assertEquals(expectedPencilLengthAfterSharpening, pencil.getPencilLength());
	}

	@Test
	public void testSharpeningPencilWithNoLengthDoesNotRestoreDurability() throws Exception {
		int originalDurability = PENCIL_DURABILITY_500;
		PencilInterface pencil = new Pencil(originalDurability, PENCIL_LENGTH_0,
				ERASER_DURABILITY_25);

		pencil.writeOnPaper(new MockPaper(), "The turtle moves.");
		int expectedFinalDurability = pencil.getCurrentPencilDurability();
		assertTrue(expectedFinalDurability < originalDurability);

		pencil.sharpen();

		int actualFinalDurability = pencil.getCurrentPencilDurability();
		assertEquals(expectedFinalDurability, actualFinalDurability);
	}

	@Test
	public void testErase_ErasesSomeWordsFromThePaper() throws Exception {
		int eraserDurability = ERASER_DURABILITY_25;
		PencilInterface pencil = new Pencil(PENCIL_DURABILITY_500, PENCIL_LENGTH_5,
				eraserDurability);
		MockPaper mockPaper = new MockPaper();

		String expectedTextErased = "Erase me!";
		pencil.erase(mockPaper, expectedTextErased);

		assertTrue(mockPaper.replaceWithWhitespaceWasCalled());
		assertEquals(expectedTextErased, mockPaper.getTextPassedToReplaceWithWhitespace());
		assertEquals(eraserDurability,
				mockPaper.getEraserDurabilityPassedToReplaceWithWhitespace());
	}

	@Test
	public void testErase_ReducesEraserDurability_ByOneForEachCharacterErased_ExceptWhitespace()
			throws Exception {
		int initialEraserDurability = 200;
		PencilInterface pencil = new Pencil(PENCIL_DURABILITY_500, PENCIL_LENGTH_5,
				initialEraserDurability);
		MockPaper mockPaper = new MockPaper();

		String textToErase = "'The time has come,' the Walrus said,\n"
				+ "'To talk of many things:\n" + "Of shoes... and ships... and sealing-wax...\n"
				+ "Of cabbages... and kings...\n" + "\tAnd why the sea is boiling hot...\n"
				+ "\tAnd whether pigs have wings.'";

		int numberOfNonWhitespaceCharacters = findNumberOfNonWhitespaceCharacters(textToErase);

		pencil.erase(mockPaper, textToErase);
		int actualFinalEraserDurability = pencil.getEraserDurability();

		int expectedFinalEraserDurability = initialEraserDurability
				- numberOfNonWhitespaceCharacters;

		assertEquals(expectedFinalEraserDurability, actualFinalEraserDurability);
	}

	@Test
	public void testErase_EraserDurabilityCannotFallBelowZero() throws Exception {
		PencilInterface pencil = new Pencil(PENCIL_DURABILITY_500, PENCIL_LENGTH_5,
				ERASER_DURABILITY_25);

		String textThatWouldReduceEraserDurabilityToNegative = "Twas brillig, and the slithy toves did gyre and gimble in the wabe:"
				+ "All mimsy were the borogoves, and the mome raths outgrabe.";

		pencil.erase(new MockPaper(), textThatWouldReduceEraserDurabilityToNegative);

		int actualResultingEraserDurability = pencil.getEraserDurability();
		int expectedResultingEraserDurability = 0;

		assertEquals(expectedResultingEraserDurability, actualResultingEraserDurability);
	}

	@Test
	public void testErase_EraserDoesNotDegradeIfNothingIsErased() throws Exception {
		PencilInterface pencil = new Pencil(PENCIL_DURABILITY_500, PENCIL_LENGTH_5,
				ERASER_DURABILITY_25);
		MockPaper mockPaper = new MockPaper();
		mockPaper.setSomethingWasErased(false);

		pencil.erase(mockPaper, "Hola, señorita. ¿Cómo te llamas?");

		int expectedEraserDurability = ERASER_DURABILITY_25;
		assertEquals(expectedEraserDurability, pencil.getEraserDurability());
	}

	private int findNumberOfNonWhitespaceCharacters(String text) {
		int numberOfTotalCharacters = text.length();
		int numberOfWhitespaceCharacters = text.split("\\s").length - 1;
		int numberOfNonWhitespaceCharacters = numberOfTotalCharacters
				- numberOfWhitespaceCharacters;
		return numberOfNonWhitespaceCharacters;
	}

}
