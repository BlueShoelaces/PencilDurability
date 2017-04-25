package nicole.durability;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class PaperTest {

	@Test
	public void testPaperCanBeWrittenOn() throws Exception {
		ByteArrayOutputStream mockOutputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(mockOutputStream));

		Paper paper = new Paper();
		String expectedStringWrittenToPaper = "It is a truth universally acknowledged ";
		paper.write(expectedStringWrittenToPaper);

		assertEquals(expectedStringWrittenToPaper, mockOutputStream.toString());
	}
}
