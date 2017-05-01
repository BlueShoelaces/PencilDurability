package nicole.durability;

public class Pencil implements PencilInterface {

	private int durability;

	public Pencil() {

	}

	public Pencil(int durability) {
		this.durability = durability;
	}

	@Override
	public void writeOnPaper(PaperInterface paper, String textToWrite) {
		this.durability -= textToWrite.length();
		paper.write(textToWrite);
	}

	@Override
	public int getDurability() {
		return this.durability;
	}

}
