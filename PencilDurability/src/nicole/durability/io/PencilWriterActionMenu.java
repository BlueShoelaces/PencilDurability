package nicole.durability.io;

import java.util.*;

import nicole.durability.actions.*;

public class PencilWriterActionMenu {

	private List<WritePencilAction> menuActions;

	public PencilWriterActionMenu() {
		this.menuActions = new ArrayList<WritePencilAction>();
	}

	public void openMainMenu() {
		this.menuActions.add(new WritePencilAction(null, null));
	}

	public List<WritePencilAction> getMenuActions() {
		return this.menuActions;
	}

}
