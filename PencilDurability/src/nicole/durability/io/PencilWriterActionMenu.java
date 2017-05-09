package nicole.durability.io;

import java.util.*;

import nicole.durability.*;
import nicole.durability.actions.*;

public class PencilWriterActionMenu {

	private List<PencilAction> menuActions;
	private DisplayHelperInterface displayHelper;

	public PencilWriterActionMenu(DisplayHelperInterface displayHelper) {
		this.menuActions = new ArrayList<PencilAction>();
		this.displayHelper = displayHelper;
	}

	public void openMainMenu() {
		int pencilDurability = 500;
		int pencilLength = 4;
		this.menuActions.add(
				new WritePencilAction(new Pencil(pencilDurability, pencilLength), new Paper()));
		this.displayHelper.display(this.menuActions);
	}

	public List<PencilAction> getMenuActions() {
		return this.menuActions;
	}

	public DisplayHelperInterface getDisplayHelper() {
		return this.displayHelper;
	}

}
