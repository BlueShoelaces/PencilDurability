package nicole.durability;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.*;

import org.junit.*;

import nicole.durability.io.*;
import nicole.durability.mocks.*;
import nicole.test.*;

public class LauncherTest extends TestHelper {

	private Field pencilWriterSingletonPrivateField;
	private MockPencilWriterSingleton mockPencilWriter;

	@Before
	public void setUp() throws Exception {
		this.pencilWriterSingletonPrivateField = PencilWriterSingleton.class
				.getDeclaredField("instance");
		this.pencilWriterSingletonPrivateField.setAccessible(true);

		this.mockPencilWriter = new MockPencilWriterSingleton();
		this.pencilWriterSingletonPrivateField.set(null, this.mockPencilWriter);
	}

	@After
	public void tearDown() throws Exception {
		this.pencilWriterSingletonPrivateField.set(null, null);
	}

	@Test
	public void testMain() throws Exception {
		Launcher.main(null);
		assertTrue(this.mockPencilWriter.runWasCalled());

		PencilWriterActionMenu menuPassedToRun = assertIsOfTypeAndGet(PencilWriterActionMenu.class,
				this.mockPencilWriter.getMenuPassedToRun());

		assertIsOfTypeAndGet(PencilWriterActionMenuDisplayHelper.class,
				menuPassedToRun.getDisplayHelper());
	}

}
