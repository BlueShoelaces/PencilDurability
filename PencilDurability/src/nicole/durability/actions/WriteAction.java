package nicole.durability.actions;

import nicole.durability.*;
import nicole.durability.io.*;

public class WriteAction implements MenuAction {

	private PencilInterface pencil;
	private PaperInterface paper;

	public WriteAction(PencilInterface pencil, PaperInterface paper) {
		this.pencil = pencil;
		this.paper = paper;
	}

	@Override
	public void perform() {
		InputScannerWrapperSingletonInterface scannerWrapper = InputScannerWrapperSingleton
				.instance();
		System.out.print("Enter some text to write: ");
		String textToWrite = scannerWrapper.nextLine();
		this.pencil.writeOnPaper(this.paper, textToWrite);
	}

	public PencilInterface getPencil() {
		return this.pencil;
	}

	public PaperInterface getPaper() {
		return this.paper;
	}

	@Override
	public String getMenuTextToDisplay() {
		return "Write something";
	}

}
