package nicole.durability;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import nicole.durability.mocks.*;
import nicole.test.*;

public class PencilTest extends TestHelper {

	private static final int DEFAULT_DURABILITY = 50;

	@Test
	public void testImplementsInterface() throws Exception {
		assertImplementsInterface(Pencil.class, PencilInterface.class);
	}

	@Test
	public void testPencilCanBeCreatedWithDurability() {
		int expectedDurability = 1500;
		PencilInterface pencil = new Pencil(expectedDurability);
		int actualDurability = pencil.getDurability();

		assertEquals(expectedDurability, actualDurability);
	}

	@Test
	public void testPencilGetsDefaultDurability() throws Exception {
		Pencil pencil = new Pencil();
		int actualDurability = pencil.getDurability();

		assertEquals(DEFAULT_DURABILITY, actualDurability);

	}

	@Test
	public void testWriteOnPaper() throws Exception {
		PencilInterface pencil = new Pencil();
		MockPaper mockPaper = new MockPaper();
		String expectedTextWrittenToPaper = "What a long, strange trip it's been.";

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

}
