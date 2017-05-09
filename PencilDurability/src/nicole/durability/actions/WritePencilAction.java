package nicole.durability.actions;

import nicole.durability.*;
import nicole.durability.io.*;

public class WritePencilAction implements PencilAction {

	private PencilInterface pencil;
	private PaperInterface paper;

	public WritePencilAction(PencilInterface pencil, PaperInterface paper) {
		this.pencil = pencil;
		this.paper = paper;
	}

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
	public String getTextToDisplay() {
		return null;
	}

}
