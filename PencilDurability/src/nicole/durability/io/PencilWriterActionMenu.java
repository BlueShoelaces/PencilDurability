package nicole.durability.io;

import java.util.*;

import nicole.durability.*;
import nicole.durability.actions.*;

public class PencilWriterActionMenu implements PencilWriterActionMenuInterface {

	private List<PencilAction> menuActions;
	private DisplayHelperInterface displayHelper;

	public PencilWriterActionMenu(DisplayHelperInterface displayHelper) {
		createActionsForMainMenu();
		this.displayHelper = displayHelper;
	}

	@Override
	public void openMainMenu() {
		this.displayHelper.displayMainMenuWithUserPrompt(this.menuActions);
		String userInputMenuChoice = InputScannerWrapperSingleton.instance().nextLine();

		try {
			int indexOfMenuChoice = Integer.parseInt(userInputMenuChoice) - 1;

			boolean indexIsNotTooLarge = indexOfMenuChoice < this.menuActions.size();
			boolean indexIsNotTooSmall = indexOfMenuChoice >= 0;

			boolean indexIsJustRight = indexIsNotTooLarge && indexIsNotTooSmall;

			if (indexIsJustRight) {
				this.menuActions.get(indexOfMenuChoice).perform();
			}

		} catch (NumberFormatException exception) {

		}
	}

	@Override
	public List<PencilAction> getMenuActions() {
		return this.menuActions;
	}

	public DisplayHelperInterface getDisplayHelper() {
		return this.displayHelper;
	}

	private void createActionsForMainMenu() {
		this.menuActions = new ArrayList<PencilAction>();
		int pencilDurability = 500;
		int pencilLength = 4;
		this.menuActions.add(
				new WritePencilAction(new Pencil(pencilDurability, pencilLength), new Paper()));
	}
}
