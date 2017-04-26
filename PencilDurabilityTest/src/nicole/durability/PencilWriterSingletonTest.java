package nicole.durability;

import static org.junit.Assert.assertSame;

import org.junit.*;

import nicole.test.*;

public class PencilWriterSingletonTest extends TestHelper {

	@Test
	public void testAlwaysReturnsTheSameInstance() throws Exception {
		PencilWriterSingleton firstInstance = PencilWriterSingleton.instance();
		assertIsOfType(PencilWriterSingleton.class, firstInstance);

		PencilWriterSingleton secondInstance = PencilWriterSingleton.instance();
		assertIsOfType(PencilWriterSingleton.class, secondInstance);

		assertSame(firstInstance, secondInstance);
	}
}
