package nicole.durability.actions;

import org.junit.*;

import nicole.test.*;

public class ShowPaperActionTest extends TestHelper {

	@Test
	public void testImplementsInterface() throws Exception {
		assertImplementsInterface(ShowPaperAction.class, PencilAction.class);
	}

}
