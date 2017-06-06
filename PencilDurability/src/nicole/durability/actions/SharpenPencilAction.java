package nicole.durability.actions;

import nicole.durability.*;

public class SharpenPencilAction implements MenuAction {

	private PencilInterface pencil;

	public SharpenPencilAction(PencilInterface pencil) {
		this.pencil = pencil;
	}

	public PencilInterface getPencil() {
		return this.pencil;
	}

	@Override
	public String getMenuTextToDisplay() {
		return null;
	}

	@Override
	public void perform() {
		this.pencil.sharpen();
	}

}
