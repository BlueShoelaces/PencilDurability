package nicole.durability;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.*;

import nicole.durability.mocks.*;
import nicole.test.*;

public class PencilWriterSingletonTest extends TestHelper {

	private String expectedTextToWrite = "Play it, Sam. Play 'As Time Goes By.'\n";

	@Test
	public void testImplementsInterface() throws Exception {
		assertImplementsInterface(PencilWriterSingleton.class,
				PencilWriterSingletonInterface.class);
	}

	@Test
	public void testAlwaysReturnsTheSameInstance() throws Exception {
		PencilWriterSingletonInterface firstInstance = PencilWriterSingleton.instance();
		assertIsOfType(PencilWriterSingleton.class, firstInstance);

		PencilWriterSingletonInterface secondInstance = PencilWriterSingleton.instance();
		assertIsOfType(PencilWriterSingleton.class, secondInstance);

		assertSame(firstInstance, secondInstance);
	}

	@Test
	public void testRunWritesSomethingToPaper() throws Exception {
		MockPaper expectedPaper = new MockPaper();
		MockPencil mockPencil = new MockPencil();

		PencilWriterSingleton.instance().run(expectedPaper, mockPencil);

		PaperInterface actualPaperWrittenToWithPencil = mockPencil.getPaperWrittenTo();
		String actualTextToWrite = mockPencil.getTextWrittenToPaper();

		assertSame(expectedPaper, actualPaperWrittenToWithPencil);
		assertEquals(this.expectedTextToWrite, actualTextToWrite);
	}
}
