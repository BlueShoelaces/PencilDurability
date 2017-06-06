package nicole.durability;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import nicole.durability.mocks.*;
import nicole.test.*;

public class PencilTest extends TestHelper {

	private static final int PENCIL_LENGTH_0 = 0;
	private static final int PENCIL_LENGTH_5 = 5;
	private static final int DURABILITY_500 = 500;

	@Test
	public void testImplementsInterface() throws Exception {
		assertImplementsInterface(Pencil.class, PencilInterface.class);
	}

	@Test
	public void testPencilConstructor() {
		int expectedDurability = 1500;
		int expectedPencilLength = PENCIL_LENGTH_5;

		PencilInterface pencil = new Pencil(expectedDurability, expectedPencilLength);
		int actualDurability = pencil.getCurrentDurability();
		int actualPencilLength = pencil.getPencilLength();

		assertEquals(expectedDurability, actualDurability);
		assertEquals(expectedPencilLength, actualPencilLength);
	}

	@Test
	public void testWriteOnPaper() throws Exception {
		String expectedTextWrittenToPaper = "What a long, strange trip it's been.";
		int durability = expectedTextWrittenToPaper.length() * 2;

		PencilInterface pencil = new Pencil(durability, PENCIL_LENGTH_5);
		MockPaper mockPaper = new MockPaper();

		pencil.writeOnPaper(mockPaper, expectedTextWrittenToPaper);

		String actualTextWrittenToPaper = mockPaper.getTextPassedToWrite();
		assertEquals(expectedTextWrittenToPaper, actualTextWrittenToPaper);
	}

	@Test
	public void testPencilPointDegradesWithUse_lowerCaseExpendsOneGraphitePoint() throws Exception {
		int startingDurability = 1500;
		PencilInterface pencil = new Pencil(startingDurability, PENCIL_LENGTH_5);
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		pencil.writeOnPaper(new MockPaper(), lowerCaseLetters);

		int expectedDurabilityAfterWriting = startingDurability - 26;
		int actualDurabilityAfterWriting = pencil.getCurrentDurability();

		assertEquals(expectedDurabilityAfterWriting, actualDurabilityAfterWriting);
	}

	@Test
	public void testPencilPointDegradesWithUse_punctuationAndNumbersExpendOneGraphitePoint()
			throws Exception {
		int startingDurability = 1500;
		PencilInterface pencil = new Pencil(startingDurability, PENCIL_LENGTH_5);
		String punctuation = ".?!,;:-()[]{}'\"0123456789";
		pencil.writeOnPaper(new MockPaper(), punctuation);

		int expectedDurabilityAfterWriting = startingDurability - 25;
		int actualDurabilityAfterWriting = pencil.getCurrentDurability();

		assertEquals(expectedDurabilityAfterWriting, actualDurabilityAfterWriting);
	}

	@Test
	public void testPencilPointDegradesWithUse_upperCaseExpendsTwoGraphitePoints()
			throws Exception {
		int startingDurability = 1500;
		PencilInterface pencil = new Pencil(startingDurability, PENCIL_LENGTH_5);
		String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		pencil.writeOnPaper(new MockPaper(), upperCaseLetters);

		int expectedDurabilityAfterWriting = startingDurability - (2 * 26);
		int actualDurabilityAfterWriting = pencil.getCurrentDurability();

		assertEquals(expectedDurabilityAfterWriting, actualDurabilityAfterWriting);
	}

	@Test
	public void testPencilPointDegradesWithUse_whitespaceExpendsNoGraphite() throws Exception {
		int startingDurability = 1500;
		PencilInterface pencil = new Pencil(startingDurability, PENCIL_LENGTH_5);
		String whitespace = "\t   \n";
		pencil.writeOnPaper(new MockPaper(), whitespace);

		int expectedDurabilityAfterWriting = startingDurability;
		int actualDurabilityAfterWriting = pencil.getCurrentDurability();

		assertEquals(expectedDurabilityAfterWriting, actualDurabilityAfterWriting);
	}

	@Test
	public void testDullPencilWritesOnlyBlankSpaces() throws Exception {
		int durability = 0;
		PencilInterface pencil = new Pencil(durability, PENCIL_LENGTH_5);
		MockPaper mockPaper = new MockPaper();

		String textPencilIsDirectedToWrite = "I should sharpen my pencil...";
		pencil.writeOnPaper(mockPaper, textPencilIsDirectedToWrite);

		String expectedTextWritten = "                             ";
		String actualTextWritten = mockPaper.getTextPassedToWrite();

		assertEquals(expectedTextWritten, actualTextWritten);
	}

	@Test
	public void testSharpeningPencilRestoresOriginalDurability() throws Exception {

		PaperInterface mockPaper = new MockPaper();
		int originalDurability1 = 500;
		PencilInterface pencil1 = new Pencil(originalDurability1, PENCIL_LENGTH_5);

		String textToWrite = "BILL: Pai Mei taught you the Five Point Palm Exploding Heart Technique?\n";
		pencil1.writeOnPaper(mockPaper, textToWrite);

		assertTrue(pencil1.getCurrentDurability() < originalDurability1);

		pencil1.sharpen();

		assertEquals(pencil1.getCurrentDurability(), originalDurability1);

		int originalDurability2 = 50;
		PencilInterface pencil2 = new Pencil(originalDurability2, PENCIL_LENGTH_5);
		pencil2.writeOnPaper(mockPaper, "BRIDE: Course he did.\n");

		assertTrue(pencil2.getCurrentDurability() < originalDurability2);

		pencil2.sharpen();

		assertEquals(pencil2.getCurrentDurability(), originalDurability2);
	}

	@Test
	public void testSharpeningPencilMakesItShorter() throws Exception {
		int originalPencilLength = PENCIL_LENGTH_5;
		PencilInterface pencil = new Pencil(DURABILITY_500, originalPencilLength);

		int expectedPencilLengthAfterSharpening = originalPencilLength - 1;
		pencil.sharpen();

		assertEquals(expectedPencilLengthAfterSharpening, pencil.getPencilLength());

		expectedPencilLengthAfterSharpening = expectedPencilLengthAfterSharpening - 1;
		pencil.sharpen();

		assertEquals(expectedPencilLengthAfterSharpening, pencil.getPencilLength());
	}

	@Test
	public void testSharpeningPencilWithNoLengthDoesNotRestoreDurability() throws Exception {
		int originalDurability = DURABILITY_500;
		PencilInterface pencil = new Pencil(originalDurability, PENCIL_LENGTH_0);

		pencil.writeOnPaper(new MockPaper(), "The turtle moves.");
		int expectedFinalDurability = pencil.getCurrentDurability();
		assertTrue(expectedFinalDurability < originalDurability);

		pencil.sharpen();

		int actualFinalDurability = pencil.getCurrentDurability();
		assertEquals(expectedFinalDurability, actualFinalDurability);
	}

	@Test
	public void testErase() throws Exception {
		PencilInterface pencil = new Pencil(DURABILITY_500, PENCIL_LENGTH_5);
		MockPaper mockPaper = new MockPaper();

		String expectedTextErased = "Erase me!";
		pencil.erase(mockPaper, expectedTextErased);

		assertTrue(mockPaper.replaceWithWhitespaceWasCalled());
		assertEquals(expectedTextErased, mockPaper.getTextPassedToReplaceWithWhitespace());
	}

}
