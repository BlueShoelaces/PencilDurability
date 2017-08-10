package nicole.durability.actions;

import nicole.durability.*;

public class EditAction implements MenuAction {

	private PencilInterface pencil;
	private PaperInterface paper;

	public EditAction(PencilInterface pencil, PaperInterface paper) {
		this.pencil = pencil;
		this.paper = paper;
	}

	@Override
	public String getMenuTextToDisplay() {
		return "Write over previously erased text";
	}

	@Override
	public void perform() {
		System.out.print("Enter some text to write: ");
	}

	public PencilInterface getPencil() {
		return this.pencil;
	}

	public PaperInterface getPaper() {
		return this.paper;
	}

}
