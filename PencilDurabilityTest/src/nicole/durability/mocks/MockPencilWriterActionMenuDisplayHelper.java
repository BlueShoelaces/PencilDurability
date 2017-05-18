package nicole.durability.mocks;

import java.util.*;

import nicole.durability.actions.*;
import nicole.durability.io.*;

public class MockPencilWriterActionMenuDisplayHelper implements DisplayHelperInterface {

	private List<PencilAction> pencilActionsPassedToDisplay;

	@Override
	public void displayMainMenuWithUserPrompt(List<PencilAction> pencilActionsToDisplay) {
		this.pencilActionsPassedToDisplay = pencilActionsToDisplay;
	}

	public List<PencilAction> getActionListPassedToDisplay() {
		return this.pencilActionsPassedToDisplay;
	}

}
