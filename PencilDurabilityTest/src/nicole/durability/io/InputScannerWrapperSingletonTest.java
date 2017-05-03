package nicole.durability.io;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.*;

public class InputScannerWrapperSingletonTest {
	@Test
	public void testAlwaysReturnsSameInstance() throws Exception {
		InputScannerWrapperSingleton firstInstance = InputScannerWrapperSingleton.instance();
		assertNotNull(firstInstance);

		InputScannerWrapperSingleton secondInstance = InputScannerWrapperSingleton.instance();
		assertSame(firstInstance, secondInstance);
	}
}
