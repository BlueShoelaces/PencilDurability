package nicole.durability.io;

import java.util.*;

import nicole.durability.actions.*;

public class PencilWriterActionMenuDisplayHelper extends PencilWriterActionMenu {

	public void display(List<PencilAction> pencilActionsToDisplay) {
		String textToDisplay = "";
		for (PencilAction pencilAction : pencilActionsToDisplay) {
			textToDisplay += pencilAction.getTextToDisplay() + "\n";
		}
		System.out.print(textToDisplay);
	}

}
