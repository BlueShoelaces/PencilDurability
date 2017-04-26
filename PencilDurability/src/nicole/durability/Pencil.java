package nicole.durability;

public class Pencil implements PencilInterface {

	@Override
	public void writeOnPaper(PaperInterface paper, String textToWrite) {
		paper.write(textToWrite);
	}

}
