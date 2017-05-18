package nicole.durability.mocks;

import java.util.*;

import nicole.durability.actions.*;
import nicole.durability.io.*;

public class MockPencilWriterActionMenuDisplayHelper implements DisplayHelperInterface {

	private List<MenuAction> pencilActionsPassedToDisplay;

	@Override
	public void displayMainMenuWithUserPrompt(List<MenuAction> pencilActionsToDisplay) {
		this.pencilActionsPassedToDisplay = pencilActionsToDisplay;
	}

	public List<MenuAction> getActionListPassedToDisplay() {
		return this.pencilActionsPassedToDisplay;
	}

}
