package nicole.durability;

import nicole.durability.io.*;

public class Launcher {

	public static void main(String[] args) {
		PencilWriterSingletonInterface pencilWriter = PencilWriterSingleton.instance();

		pencilWriter.run(new PencilWriterActionMenu(new PencilWriterActionMenuDisplayHelper()));
	}

}
