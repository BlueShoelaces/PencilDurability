package nicole.durability;

public class Pencil implements PencilInterface {

	private static final int UPPERCASE_DEGRADATION_VALUE = 2;
	private static final int LOWERCASE_DEGRADATION_VALUE = 1;
	private int durability;

	public Pencil() {

	}

	public Pencil(int durability) {
		this.durability = durability;
	}

	@Override
	public void writeOnPaper(PaperInterface paper, String textToWrite) {

		for (int characterIndex = 0; characterIndex < textToWrite.length(); characterIndex++) {
			if (Character.isLowerCase(textToWrite.charAt(characterIndex))) {
				this.durability -= LOWERCASE_DEGRADATION_VALUE;
			} else {
				this.durability -= UPPERCASE_DEGRADATION_VALUE;
			}
		}

		paper.write(textToWrite);
	}

	@Override
	public int getDurability() {
		return this.durability;
	}

}
