package nicole.durability;

import static org.junit.Assert.assertSame;

import org.junit.*;

import nicole.test.*;

public class PencilWriterSingletonTest extends TestHelper {

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
}
