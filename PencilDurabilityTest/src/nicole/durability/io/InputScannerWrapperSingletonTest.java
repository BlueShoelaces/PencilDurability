package nicole.durability.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.*;

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
	}

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

	@Test
	public void testNextLine() throws Exception {
		InputScannerWrapperSingleton inputScannerWrapper = InputScannerWrapperSingleton.instance();
		String expectedNextLine = "It doesn't have to rhyme";
		System.setIn(new ByteArrayInputStream(expectedNextLine.getBytes()));

		String actualNextLine = inputScannerWrapper.nextLine();
		assertEquals(expectedNextLine, actualNextLine);

		expectedNextLine = "So don't you feed me a line";
		System.setIn(new ByteArrayInputStream(expectedNextLine.getBytes()));

		actualNextLine = inputScannerWrapper.nextLine();
		assertEquals(expectedNextLine, actualNextLine);
	}
}
