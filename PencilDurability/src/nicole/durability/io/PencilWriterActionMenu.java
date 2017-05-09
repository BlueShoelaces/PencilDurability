package nicole.durability.io;

import java.util.*;

import nicole.durability.*;
import nicole.durability.actions.*;

public class PencilWriterActionMenu {

	private List<WritePencilAction> menuActions;

	public PencilWriterActionMenu() {
		this.menuActions = new ArrayList<WritePencilAction>();
	}

	public void openMainMenu() {
		int pencilDurability = 500;
		int pencilLength = 4;
		this.menuActions.add(new WritePencilAction(new Pencil(pencilDurability, pencilLength), new Paper()));
	}

	public List<WritePencilAction> getMenuActions() {
		return this.menuActions;
	}

}
