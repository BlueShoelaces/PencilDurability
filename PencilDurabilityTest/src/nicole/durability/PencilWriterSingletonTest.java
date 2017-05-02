package nicole.durability;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.*;

import org.junit.*;

import nicole.durability.mocks.*;
import nicole.test.*;

public class PencilWriterSingletonTest extends TestHelper {

	private static final int PENCIL_LENGTH_5 = 5;
	private static final int DURABILITY_500 = 500;
	private String expectedTextToWrite = "Play it, Sam. Play 'As Time Goes By.'\n";
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
		assertImplementsInterface(PencilWriterSingleton.class,
				PencilWriterSingletonInterface.class);
	}

	@Test
	public void testAlwaysReturnsTheSameInstance() throws Exception {
		PencilWriterSingletonInterface firstInstance = PencilWriterSingleton.instance();
		assertIsOfTypeAndGet(PencilWriterSingleton.class, firstInstance);

		PencilWriterSingletonInterface secondInstance = PencilWriterSingleton.instance();
		assertIsOfTypeAndGet(PencilWriterSingleton.class, secondInstance);

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

	@Test
	public void testRunShowsPaperAfterWritingToIt_functional() throws Exception {
		Paper mockPaper = new Paper();
		Pencil mockPencil = new Pencil(DURABILITY_500, PENCIL_LENGTH_5);

		PencilWriterSingleton.instance().run(mockPaper, mockPencil);

		String actualTextToShow = this.mockOutputStream.toString();

		assertEquals(this.expectedTextToWrite, actualTextToShow);
	}
}
