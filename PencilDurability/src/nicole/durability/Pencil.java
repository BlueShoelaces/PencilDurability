package nicole.durability;

public class Pencil implements PencilInterface {

	private static final int UPPERCASE_DEGRADATION_VALUE = 2;
	private static final int LOWERCASE_DEGRADATION_VALUE = 1;
	private static final int PUNCTUATION_AND_NUMBER_DEGRADATION_VALUE = 1;

	private int currentPencilDurability;
	private int startingPencilDurability;
	private int pencilLength;
	private int eraserDurability;

	public Pencil(int pencilDurability, int pencilLength, int eraserDurability) {
		this.startingPencilDurability = this.currentPencilDurability = pencilDurability;
		this.pencilLength = pencilLength;
		this.eraserDurability = eraserDurability;
	}

	@Override
	public void writeOnPaper(PaperInterface paper, String textToWrite) {

		int numberOfCharactersInText = textToWrite.length();
		char[] charactersToWriteToPaper = new char[numberOfCharactersInText];

		for (int characterIndex = 0; characterIndex < numberOfCharactersInText; characterIndex++) {

			char characterToWrite = textToWrite.charAt(characterIndex);
			if (this.currentPencilDurability > 0) {
				charactersToWriteToPaper[characterIndex] = characterToWrite;
				degradePencil(characterToWrite);
			} else {
				charactersToWriteToPaper[characterIndex] = ' ';
			}
		}

		String textToWriteConsideringPencilSharpness = new String(charactersToWriteToPaper);

		paper.write(textToWriteConsideringPencilSharpness);
	}

	@Override
	public int getCurrentPencilDurability() {
		return this.currentPencilDurability;
	}

	@Override
	public int getPencilLength() {
		return this.pencilLength;
	}

	@Override
	public void erase(PaperInterface paper, String textToErase) {
		paper.replaceWithWhitespace(textToErase);
		int numberOfWhitespaceCharacters = textToErase.split("\\s").length - 1;
		int amountErasedCharactersAffectEraserDegradation = textToErase.length()
				- numberOfWhitespaceCharacters;
		this.eraserDurability -= amountErasedCharactersAffectEraserDegradation;
	}

	@Override
	public void sharpen() {
		if (this.pencilLength > 0) {
			this.currentPencilDurability = this.startingPencilDurability;
			this.pencilLength--;
		}
	}

	@Override
	public int getEraserDurability() {
		return this.eraserDurability;
	}

	private void degradePencil(char characterToWrite) {
		if (Character.isUpperCase(characterToWrite)) {
			this.currentPencilDurability -= UPPERCASE_DEGRADATION_VALUE;
		} else if (Character.isLowerCase(characterToWrite)) {
			this.currentPencilDurability -= LOWERCASE_DEGRADATION_VALUE;
		} else if (characterIsNumber(characterToWrite)
				|| characterIsPunctuation(characterToWrite)) {
			this.currentPencilDurability -= PUNCTUATION_AND_NUMBER_DEGRADATION_VALUE;
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
}
