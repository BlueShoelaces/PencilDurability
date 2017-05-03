package nicole.durability;

public class Pencil implements PencilInterface {

	private static final int UPPERCASE_DEGRADATION_VALUE = 2;
	private static final int LOWERCASE_DEGRADATION_VALUE = 1;
	private static final int PUNCTUATION_AND_NUMBER_DEGRADATION_VALUE = 1;

	private int currentDurability;
	private int startingDurability;
	private int pencilLength;

	public Pencil(int durability, int pencilLength) {
		this.startingDurability = this.currentDurability = durability;
		this.pencilLength = pencilLength;
	}

	@Override
	public void writeOnPaper(PaperInterface paper, String textToWrite) {

		int numberOfCharactersInText = textToWrite.length();
		char[] charactersToWriteToPaper = new char[numberOfCharactersInText];

		for (int characterIndex = 0; characterIndex < numberOfCharactersInText; characterIndex++) {

			char characterToWrite = textToWrite.charAt(characterIndex);
			if (this.currentDurability > 0) {
				charactersToWriteToPaper[characterIndex] = characterToWrite;
				degradePencil(characterToWrite);
			} else {
				charactersToWriteToPaper[characterIndex] = ' ';
			}
		}

		String textToWriteConsideringPencilSharpness = new String(charactersToWriteToPaper);

		paper.write(textToWriteConsideringPencilSharpness);
	}

	private void degradePencil(char characterToWrite) {
		if (Character.isUpperCase(characterToWrite)) {
			this.currentDurability -= UPPERCASE_DEGRADATION_VALUE;
		} else if (Character.isLowerCase(characterToWrite)) {
			this.currentDurability -= LOWERCASE_DEGRADATION_VALUE;
		} else if (characterIsNumber(characterToWrite)
				|| characterIsPunctuation(characterToWrite)) {
			this.currentDurability -= PUNCTUATION_AND_NUMBER_DEGRADATION_VALUE;
		}
	}

	private boolean characterIsNumber(char character) {
		return (character == '0' || character == '1' || character == '2' || character == '3'
				|| character == '4' || character == '5' || character == '6' || character == '7'
				|| character == '8' || character == '9');
	}

	private boolean characterIsPunctuation(char character) {
		return (character == '.' || character == '?' || character == '!' || character == ','
				|| character == ';' || character == ':' || character == '-' || character == '('
				|| character == ')' || character == '[' || character == ']' || character == '{'
				|| character == '}' || character == '\'' || character == '"');
	}

	public void sharpen() {
		if (this.pencilLength > 0) {
			this.currentDurability = this.startingDurability;
			this.pencilLength--;
		}
	}

	@Override
	public int getCurrentDurability() {
		return this.currentDurability;
	}

	public int getPencilLength() {
		return this.pencilLength;
	}

	public void erase(PaperInterface paper, String textToErase) {
		paper.replaceWithWhitespace(textToErase);
	}
}
