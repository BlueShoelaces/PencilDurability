package nicole.durability.io;

import java.util.*;

import nicole.durability.actions.*;

public class PencilWriterActionMenuDisplayHelper implements DisplayHelperInterface {

	@Override
	public void displayMainMenuWithUserPrompt(List<PencilAction> pencilActionsToDisplay) {
		String textToDisplay = "MAIN MENU:\n";
		int menuNumber = 1;
		for (PencilAction pencilAction : pencilActionsToDisplay) {
			textToDisplay += " " + menuNumber + "  " + pencilAction.getTextToDisplay() + "\n";
			menuNumber++;
		}
		textToDisplay += "Select a number and hit [ENTER]\n";
		System.out.print(textToDisplay);
	}

}
