package nicole.durability;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import nicole.durability.mocks.*;

public class PencilTest {

	@Test
	public void testWriteOnPaper() throws Exception {
		Pencil pencil = new Pencil();
		MockPaper mockPaper = new MockPaper();
		String expectedTextWrittenToPaper = "What a long, strange trip it's been.";

		pencil.writeOnPaper(mockPaper, expectedTextWrittenToPaper);

		String actualTextWrittenToPaper = mockPaper.getTextWrittenToPaper();
		assertEquals(expectedTextWrittenToPaper, actualTextWrittenToPaper);
	}
}
