package nicole.durability.mocks;

import java.util.*;

import nicole.durability.*;
import nicole.durability.actions.*;
import nicole.durability.io.*;

public class MockPencilWriterActionMenuDisplayHelper implements DisplayHelperInterface {

	private List<MenuAction> pencilActionsPassedToDisplay;
	private PencilInterface pencilPassedToDisplayPencilStats;
	private boolean displayPencilStatsWasCalled = false;;

	@Override
	public void displayMainMenuWithUserPrompt(List<MenuAction> pencilActionsToDisplay) {
		this.pencilActionsPassedToDisplay = pencilActionsToDisplay;
	}

	public List<MenuAction> getActionListPassedToDisplay() {
		return this.pencilActionsPassedToDisplay;
	}

	public PencilInterface getPencilPassedToDisplayPencilStats() {
		return this.pencilPassedToDisplayPencilStats;
	}

	@Override
	public void displayPencilStats(PencilInterface pencil) {
		this.displayPencilStatsWasCalled = true;
		this.pencilPassedToDisplayPencilStats = pencil;
	}

	public boolean displayPencilStatsWasCalled() {
		return this.displayPencilStatsWasCalled;
	}

}
