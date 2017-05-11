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
		this.displayHelper.display(this.menuActions);
		this.menuActions.get(0).perform();
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
