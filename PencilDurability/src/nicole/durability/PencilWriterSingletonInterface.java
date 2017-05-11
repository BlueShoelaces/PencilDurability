package nicole.durability;

import nicole.durability.io.*;

public interface PencilWriterSingletonInterface {

	void run(PaperInterface paper, PencilInterface pencil);

	void run(PencilWriterActionMenuInterface pencilWriterActionMenu);

}
