package nicole.durability.actions;

import nicole.durability.*;
import nicole.durability.io.*;

public class EraseAction implements MenuAction {

	private PaperInterface paper;
	private PencilInterface pencil;

	public EraseAction(PencilInterface pencil, PaperInterface paper) {
		this.pencil = pencil;
		this.paper = paper;
	}

	@Override
	public String getMenuTextToDisplay() {
		return "Erase some text";
	}

	@Override
	public void perform() {
		System.out.print("Enter some text to erase: ");
		String textToErase = InputScannerWrapperSingleton.instance().nextLine();
		this.pencil.erase(this.paper, textToErase);
	}

	public PencilInterface getPencil() {
		return this.pencil;
	}

	public PaperInterface getPaper() {
		return this.paper;
	}

}
