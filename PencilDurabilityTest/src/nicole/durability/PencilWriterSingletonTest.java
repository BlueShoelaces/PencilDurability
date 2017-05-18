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
	public void testRunLaunchesMainMenuContinually() throws Exception {
		ThreadClassForTestingRun threadClassForTestingRun = new ThreadClassForTestingRun();

		long timeout = 1000;
		long threadRunTime = 0;
		long startTime = System.currentTimeMillis();
		threadClassForTestingRun.start();

		while (threadRunTime < timeout) {
			threadRunTime = System.currentTimeMillis() - startTime;
		}

		threadClassForTestingRun.interrupt();

		int timesOpenMainMenuWasCalled = threadClassForTestingRun.timesOpenMainMenuWasCalled();

		boolean mainMenuLoopedAtLeast100Times = timesOpenMainMenuWasCalled > 100;
		assertTrue("Main Menu was only opened " + timesOpenMainMenuWasCalled + " time(s).",
				mainMenuLoopedAtLeast100Times);
	}

	private class ThreadClassForTestingRun extends Thread {
		private MockPencilWriterActionMenu mockMenu;

		public ThreadClassForTestingRun() {
			this.mockMenu = new MockPencilWriterActionMenu();
		}

		@Override
		public void run() {
			PencilWriterSingleton.instance().run(this.mockMenu);
		}

		public int timesOpenMainMenuWasCalled() {
			return this.mockMenu.getNumberOfTimesOpenMainMenuWasCalled();
		}
	}
}
