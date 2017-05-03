package nicole.durability.actions;

import nicole.durability.*;
import nicole.durability.io.*;

public class WritePencilAction {

	private PencilInterface pencil;
	private PaperInterface paper;

	public WritePencilAction(PencilInterface pencil, PaperInterface paper) {
		this.pencil = pencil;
		this.paper = paper;
	}

	public void perform() {
		InputScannerWrapperSingletonInterface scannerWrapper = InputScannerWrapperSingleton
				.instance();
		String textToWrite = scannerWrapper.nextLine();
		this.pencil.writeOnPaper(this.paper, textToWrite);
	}

}
