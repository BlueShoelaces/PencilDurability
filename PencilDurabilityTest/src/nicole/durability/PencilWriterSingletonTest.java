package nicole.durability;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.*;

import org.junit.*;

import nicole.durability.mocks.*;
import nicole.test.*;

public class PencilWriterSingletonTest extends TestHelper {
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
	public void testRunLaunchesMainMenu() throws Exception {
		MockPencilWriterActionMenu mockMenu = new MockPencilWriterActionMenu();
		PencilWriterSingleton.instance().run(mockMenu);

		boolean mainMenuWasCalled = mockMenu.openMainMenuWasCalled();
		assertTrue(mainMenuWasCalled);
	}
}
