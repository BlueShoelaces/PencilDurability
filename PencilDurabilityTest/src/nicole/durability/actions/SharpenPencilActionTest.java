package nicole.durability.actions;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import nicole.durability.mocks.*;
import nicole.test.*;

public class SharpenPencilActionTest extends TestHelper {

	@Test
	public void testImplementsInterface() throws Exception {
		assertImplementsInterface(SharpenPencilAction.class, MenuAction.class);
	}

	@Test
	public void testConstructor() throws Exception {
		MockPencil expectedPencil = new MockPencil();
		SharpenPencilAction sharpenPencilAction = new SharpenPencilAction(expectedPencil);

		assertSame(expectedPencil, sharpenPencilAction.getPencil());
	}

	@Test
	public void testPerformSharpensPencil() throws Exception {
		MockPencil mockPencil = new MockPencil();
		SharpenPencilAction sharpenPencilAction = new SharpenPencilAction(mockPencil);

		sharpenPencilAction.perform();

		assertTrue(mockPencil.sharpenWasCalled());
	}

}
