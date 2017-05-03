package nicole.durability.io;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.*;

import org.junit.*;

import nicole.test.*;

public class InputScannerWrapperSingletonTest extends TestHelper {
	@Test
	public void testAlwaysReturnsSameInstance() throws Exception {
		InputScannerWrapperSingleton firstInstance = InputScannerWrapperSingleton.instance();
		assertNotNull(firstInstance);

		InputScannerWrapperSingleton secondInstance = InputScannerWrapperSingleton.instance();
		assertSame(firstInstance, secondInstance);
	}

	@Test
	public void testHasSystemIn() throws Exception {
		InputScannerWrapperSingleton inputScannerWrapper = InputScannerWrapperSingleton.instance();
		InputStream expectedInputStream = System.in;
		InputStream actualInputStream = inputScannerWrapper.getInputStream();
		assertSame(expectedInputStream, actualInputStream);
	}
}
