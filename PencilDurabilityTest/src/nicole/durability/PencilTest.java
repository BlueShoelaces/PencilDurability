package nicole.durability;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import nicole.durability.mocks.*;
import nicole.test.*;

public class PencilTest extends TestHelper {

	@Test
	public void testImplementsInterface() throws Exception {
		assertImplementsInterface(Pencil.class, PencilInterface.class);
	}

	@Test
	public void testPencilIsCreatedWithDurability() {
		int expectedDurability = 1500;
		PencilInterface pencil = new Pencil(expectedDurability);
		int actualDurability = pencil.getDurability();

		assertEquals(expectedDurability, actualDurability);
	}

	@Test
	public void testWriteOnPaper() throws Exception {
		String expectedTextWrittenToPaper = "What a long, strange trip it's been.";
		int durability = expectedTextWrittenToPaper.length() * 2;

		PencilInterface pencil = new Pencil(durability);
		MockPaper mockPaper = new MockPaper();

		pencil.writeOnPaper(mockPaper, expectedTextWrittenToPaper);

		String actualTextWrittenToPaper = mockPaper.getTextWrittenToPaper();
		assertEquals(expectedTextWrittenToPaper, actualTextWrittenToPaper);
	}

	@Test
	public void testPencilPointDegradesWithUse_lowerCaseExpendsOneGraphitePoint() throws Exception {
		int startingDurability = 1500;
		Pencil pencil = new Pencil(startingDurability);
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		pencil.writeOnPaper(new MockPaper(), lowerCaseLetters);

		int expectedDurabilityAfterWriting = startingDurability - 26;
		int actualDurabilityAfterWriting = pencil.getDurability();

		assertEquals(expectedDurabilityAfterWriting, actualDurabilityAfterWriting);
	}

	@Test
	public void testPencilPointDegradesWithUse_punctuationAndNumbersExpendOneGraphitePoint()
			throws Exception {
		int startingDurability = 1500;
		Pencil pencil = new Pencil(startingDurability);
		String punctuation = ".?!,;:-()[]{}'\"0123456789";
		pencil.writeOnPaper(new MockPaper(), punctuation);

		int expectedDurabilityAfterWriting = startingDurability - 25;
		int actualDurabilityAfterWriting = pencil.getDurability();

		assertEquals(expectedDurabilityAfterWriting, actualDurabilityAfterWriting);
	}

	@Test
	public void testPencilPointDegradesWithUse_upperCaseExpendsTwoGraphitePoints()
			throws Exception {
		int startingDurability = 1500;
		Pencil pencil = new Pencil(startingDurability);
		String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		pencil.writeOnPaper(new MockPaper(), upperCaseLetters);

		int expectedDurabilityAfterWriting = startingDurability - (2 * 26);
		int actualDurabilityAfterWriting = pencil.getDurability();

		assertEquals(expectedDurabilityAfterWriting, actualDurabilityAfterWriting);
	}

	@Test
	public void testPencilPointDegradesWithUse_whitespaceExpendsNoGraphite() throws Exception {
		int startingDurability = 1500;
		Pencil pencil = new Pencil(startingDurability);
		String whitespace = "\t   \n";
		pencil.writeOnPaper(new MockPaper(), whitespace);

		int expectedDurabilityAfterWriting = startingDurability;
		int actualDurabilityAfterWriting = pencil.getDurability();

		assertEquals(expectedDurabilityAfterWriting, actualDurabilityAfterWriting);
	}

	@Test
	public void testDullPencilWritesOnlyBlankSpaces() throws Exception {
		int durability = 0;
		Pencil pencil = new Pencil(durability);
		MockPaper mockPaper = new MockPaper();

		String textPencilIsDirectedToWrite = "I should sharpen my pencil...";
		pencil.writeOnPaper(mockPaper, textPencilIsDirectedToWrite);

		String expectedTextWritten = "                             ";
		String actualTextWritten = mockPaper.getTextWrittenToPaper();

		assertEquals(expectedTextWritten, actualTextWritten);
	}

	@Test
	public void testSharpeningPencilRestoresOriginalDurability() throws Exception {

		MockPaper mockPaper = new MockPaper();
		int originalDurability1 = 500;
		Pencil pencil1 = new Pencil(originalDurability1);

		String textToWrite = "BILL: Pai Mei taught you the Five Point Palm Exploding Heart Technique?\n";
		pencil1.writeOnPaper(mockPaper, textToWrite);

		assertTrue(pencil1.getDurability() < originalDurability1);

		pencil1.sharpen();

		assertEquals(pencil1.getDurability(), originalDurability1);

		int originalDurability2 = 50;
		Pencil pencil2 = new Pencil(originalDurability2);
		pencil2.writeOnPaper(mockPaper, "BRIDE: Course he did.\n");

		assertTrue(pencil2.getDurability() < originalDurability2);

		pencil2.sharpen();

		assertEquals(pencil2.getDurability(), originalDurability2);
	}

}
