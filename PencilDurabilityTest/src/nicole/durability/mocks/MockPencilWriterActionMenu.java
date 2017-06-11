package nicole.durability.mocks;

import java.util.*;

import nicole.durability.actions.*;
import nicole.durability.io.*;

public class MockPencilWriterActionMenu implements PencilWriterActionMenuInterface {

	private int numberOfTimesOpenMainMenuWasCalled = 0;
	private boolean shouldQuit;

	@Override
	public List<MenuAction> getMenuActions() {
		return null;
	}

	@Override
	public boolean openMainMenuWithQuitOption() {
		this.numberOfTimesOpenMainMenuWasCalled++;
		return this.shouldQuit;
	}

	public int getNumberOfTimesOpenMainMenuWasCalled() {
		return this.numberOfTimesOpenMainMenuWasCalled;
	}

	public boolean shouldQuit() {
		return this.shouldQuit;
	}

	public void setShouldQuit(boolean shouldQuit) {
		this.shouldQuit = shouldQuit;
	}
}
