package nicole.durability.io;

import java.util.*;

import nicole.durability.*;
import nicole.durability.actions.*;

public class PencilWriterActionMenu implements PencilWriterActionMenuInterface {

	private List<MenuAction> menuActions;
	private DisplayHelperInterface displayHelper;
	private Pencil pencil;

	public PencilWriterActionMenu(DisplayHelperInterface displayHelper) {
		int pencilDurability = 500;
		int pencilLength = 4;
		this.pencil = new Pencil(pencilDurability, pencilLength);
		this.displayHelper = displayHelper;

		createActionsForMainMenu();
	}

	@Override
	public void openMainMenu() {
		this.displayHelper.displayPencilStats(this.pencil);
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
	public List<MenuAction> getMenuActions() {
		return this.menuActions;
	}

	public DisplayHelperInterface getDisplayHelper() {
		return this.displayHelper;
	}

	private void createActionsForMainMenu() {
		this.menuActions = new ArrayList<MenuAction>();
		Paper paper = new Paper();

		this.menuActions.add(new WriteAction(this.pencil, paper));
		this.menuActions.add(new ShowPaperAction(paper));
		this.menuActions.add(new EraseAction());
	}

	public Pencil getPencil() {
		return this.pencil;
	}
}
