package nicole.durability.io;

import java.util.*;

import nicole.durability.actions.*;

public class PencilWriterActionMenuDisplayHelper implements DisplayHelperInterface {

	@Override
	public void displayMainMenuWithUserPrompt(List<MenuAction> pencilActionsToDisplay) {
		String textToDisplay = "MAIN MENU:\n";
		int menuNumber = 1;
		for (MenuAction pencilAction : pencilActionsToDisplay) {
			textToDisplay += " " + menuNumber + "  " + pencilAction.getMenuTextToDisplay() + "\n";
			menuNumber++;
		}
		textToDisplay += "Select a number and hit [ENTER]\n";
		System.out.print(textToDisplay);
	}

}
