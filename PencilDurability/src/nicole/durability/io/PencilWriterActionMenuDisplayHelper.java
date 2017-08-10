package nicole.durability.io;

import java.util.*;

import nicole.durability.*;
import nicole.durability.actions.*;

public class PencilWriterActionMenuDisplayHelper implements DisplayHelperInterface {

	@Override
	public void displayMainMenuWithUserPrompt(List<MenuAction> pencilActionsToDisplay) {
		String textToDisplay = "\nMAIN MENU:\n";
		int menuNumber = 1;
		for (MenuAction pencilAction : pencilActionsToDisplay) {
			textToDisplay += " " + menuNumber + "  " + pencilAction.getMenuTextToDisplay() + "\n";
			menuNumber++;
		}
		textToDisplay += "Select a number (or 0 to quit) and hit [ENTER]\n";
		System.out.print(textToDisplay);
	}

	@Override
	public void displayPencilStats(PencilInterface pencil) {
		String textToDisplay = "\nYOUR PENCIL:\n";
		textToDisplay += "  Pencil length = " + pencil.getPencilLength() + "\n";
		textToDisplay += "  Durability = " + pencil.getCurrentPencilDurability() + "\n";
		textToDisplay += "  Eraser durability = " + pencil.getEraserDurability() + "\n";

		System.out.print(textToDisplay);
	}

}
