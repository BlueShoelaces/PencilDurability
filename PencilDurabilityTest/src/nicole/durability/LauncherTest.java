package nicole.durability;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.*;

import org.junit.*;

import nicole.durability.mocks.*;

public class LauncherTest {

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
	}

}
