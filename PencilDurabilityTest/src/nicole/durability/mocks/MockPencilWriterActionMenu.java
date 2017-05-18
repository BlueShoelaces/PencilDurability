package nicole.durability.mocks;

import java.util.*;

import nicole.durability.actions.*;
import nicole.durability.io.*;

public class MockPencilWriterActionMenu implements PencilWriterActionMenuInterface {

	private int numberOfTimesOpenMainMenuWasCalled = 0;

	@Override
	public List<MenuAction> getMenuActions() {
		return null;
	}

	@Override
	public void openMainMenu() {
		this.numberOfTimesOpenMainMenuWasCalled++;
	}

	public int getNumberOfTimesOpenMainMenuWasCalled() {
		return this.numberOfTimesOpenMainMenuWasCalled;
	}

}
