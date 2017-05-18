package nicole.durability.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.*;
import java.lang.reflect.*;

import org.junit.*;

import nicole.test.*;

public class InputScannerWrapperSingletonTest extends TestHelper {

	private InputStream standardIn;

	@Before
	public void setUp() throws Exception {
		this.standardIn = System.in;
	}

	@After
	public void tearDown() throws Exception {
		System.setIn(this.standardIn);
		resetSingleton();
	}

	@Test
	public void testAlwaysReturnsSameInstance() throws Exception {
		InputScannerWrapperSingletonInterface firstInstance = InputScannerWrapperSingleton
				.instance();
		assertNotNull(firstInstance);

		InputScannerWrapperSingletonInterface secondInstance = InputScannerWrapperSingleton
				.instance();
		assertSame(firstInstance, secondInstance);
	}

	@Test
	public void testImplementsInterface() throws Exception {
		assertImplementsInterface(InputScannerWrapperSingleton.class,
				InputScannerWrapperSingletonInterface.class);
	}

	@Test
	public void testHasSystemIn() throws Exception {
		InputScannerWrapperSingletonInterface inputScannerWrapper = InputScannerWrapperSingleton
				.instance();
		InputStream expectedInputStream = System.in;
		InputStream actualInputStream = inputScannerWrapper.getInputStream();
		assertSame(expectedInputStream, actualInputStream);
	}

	@Test
	public void testNextLine() throws Exception {
		String expectedFirstLine = "It doesn't have to rhyme";
		String expectedSecondLine = "So don't you feed me a line";
		ByteArrayInputStream mockInputStream = new ByteArrayInputStream(
				(expectedFirstLine + "\n" + expectedSecondLine).getBytes());
		System.setIn(mockInputStream);

		InputScannerWrapperSingletonInterface inputScannerWrapper = InputScannerWrapperSingleton
				.instance();

		String actualNextLine = inputScannerWrapper.nextLine();
		assertEquals(expectedFirstLine, actualNextLine);

		actualNextLine = inputScannerWrapper.nextLine();
		assertEquals(expectedSecondLine, actualNextLine);
	}

	private void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
		Field scannerWrapperField = InputScannerWrapperSingleton.class.getDeclaredField("instance");
		scannerWrapperField.setAccessible(true);
		scannerWrapperField.set(null, null);
	}
}
