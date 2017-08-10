package nicole.durability.actions;

import org.junit.*;

import nicole.test.*;

public class EditActionTest extends TestHelper {

	@Test
	public void testImplementsMenuAction() throws Exception {
		assertImplementsInterface(EditAction.class, MenuAction.class);
	}
}
