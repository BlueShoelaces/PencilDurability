package nicole.durability.mocks;

import java.util.*;

import nicole.durability.actions.*;
import nicole.durability.io.*;

public class MockPencilWriterActionMenu implements PencilWriterActionMenuInterface {

	private boolean openMainMenuWasCalled = false;

	@Override
	public List<PencilAction> getMenuActions() {
		return null;
	}

	@Override
	public void openMainMenu() {
		this.openMainMenuWasCalled = true;
	}

	public boolean openMainMenuWasCalled() {
		return this.openMainMenuWasCalled;
	}

}
