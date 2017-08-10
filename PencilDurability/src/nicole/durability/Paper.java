package nicole.durability;

public class Paper implements PaperInterface {

	private String textOnPaper = "";

	@Override
	public void write(String stringToWriteToPaper) {
		this.textOnPaper += stringToWriteToPaper + "\n";
	}

	@Override
	public void writeInWhitespaceGap(String stringToWriteToPaper) {

	}

	@Override
	public void showPaper() {
		System.out.print("\n" + this.textOnPaper + "\n");
	}

	@Override
	public void replaceWithWhitespace(String textToErase, int eraserDurability) {
		int indexOfLastOccurenceOfTextToErase = this.textOnPaper.lastIndexOf(textToErase);
		int numberOfLettersToErase = textToErase.length();

		replacePartOfTextOnPaperWithWhitespace(indexOfLastOccurenceOfTextToErase,
				numberOfLettersToErase, eraserDurability);
	}

	private void replacePartOfTextOnPaperWithWhitespace(int startOfEraseChunk,
			int numberOfLettersToErase, int eraserDurability) {
		char[] individualLettersOnPaper = this.textOnPaper.toCharArray();
		int endOfEraseChunk = startOfEraseChunk + numberOfLettersToErase;

		for (int indexOfLetterToErase = endOfEraseChunk
				- 1; indexOfLetterToErase >= startOfEraseChunk
						&& eraserDurability > 0; indexOfLetterToErase--) {
			char characterOnPaper = individualLettersOnPaper[indexOfLetterToErase];
			boolean characterOnPaperIsNotWhitespace = characterOnPaper != ' '
					&& characterOnPaper != '\n' && characterOnPaper != '\t';

			if (characterOnPaperIsNotWhitespace) {
				eraserDurability -= 1;
			}

			individualLettersOnPaper[indexOfLetterToErase] = ' ';
		}
		String newTextOnPaper = new String(individualLettersOnPaper);
		this.textOnPaper = newTextOnPaper;
	}

	public String getTextOnPaper() {
		return this.textOnPaper;
	}

}
