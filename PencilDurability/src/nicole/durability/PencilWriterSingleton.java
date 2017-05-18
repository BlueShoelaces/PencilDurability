package nicole.durability;

import nicole.durability.io.*;

public class PencilWriterSingleton implements PencilWriterSingletonInterface {

	private static PencilWriterSingletonInterface instance;

	private PencilWriterSingleton() {

	}

	public static PencilWriterSingletonInterface instance() {
		if (instance == null) {
			instance = new PencilWriterSingleton();
		}
		return instance;
	}

	@Override
	public void run(PencilWriterActionMenuInterface pencilWriterActionMenu) {
		while (true) {
			pencilWriterActionMenu.openMainMenu();
		}
	}

}
