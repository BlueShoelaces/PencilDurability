package nicole.durability;

public class Paper implements PaperInterface {

	private String textOnPaper = "";

	@Override
	public void write(String textToWriteToPaper) {
		this.textOnPaper += textToWriteToPaper + "\n";
	}

	@Override
	public void writeInWhitespaceGap(String textToWriteToPaper) {
		int indexOfFirstWhitespaceGap = this.textOnPaper.indexOf("  ");
		if (indexOfFirstWhitespaceGap >= 0) {
			int indexAtWhichToStartWriting = indexOfFirstWhitespaceGap + 1;
			replacePartOfTextOnPaperWithOtherText(indexAtWhichToStartWriting, textToWriteToPaper);
		}
	}

	@Override
	public void showPaper() {
		System.out.print("\n" + this.textOnPaper + "\n");
	}

	@Override
	public boolean replaceWithWhitespace(String textToErase, int eraserDurability) {
		int indexOfLastOccurenceOfTextToErase = this.textOnPaper.lastIndexOf(textToErase);

		if (indexOfLastOccurenceOfTextToErase >= 0) {
			int numberOfLettersToErase = textToErase.length();

			replacePartOfTextOnPaperWithWhitespace(indexOfLastOccurenceOfTextToErase,
					numberOfLettersToErase, eraserDurability);

			return true;
		}

		return false;
	}

	private void replacePartOfTextOnPaperWithOtherText(int startOfOverwrittenChunk,
			String textToWrite) {
		int numberOfLettersToErase = textToWrite.length();
		char[] individualLettersOnPaper = this.textOnPaper.toCharArray();
		int endOfOverwrittenChunk = startOfOverwrittenChunk + numberOfLettersToErase;

		int indexOfLetterToWrite = 0;
		for (int indexOfLetterToOverwrite = startOfOverwrittenChunk; indexOfLetterToOverwrite < endOfOverwrittenChunk; indexOfLetterToOverwrite++) {
			char characterOnPaper = individualLettersOnPaper[indexOfLetterToOverwrite];
			boolean characterOnPaperIsWhitespace = isWhitespace(characterOnPaper);
			boolean characterToWriteIsWhitespace = isWhitespace(
					textToWrite.charAt(indexOfLetterToWrite));

			if (characterOnPaperIsWhitespace) {
				individualLettersOnPaper[indexOfLetterToOverwrite] = textToWrite
						.charAt(indexOfLetterToWrite++);
			} else if (characterToWriteIsWhitespace) {
				indexOfLetterToWrite++;
			} else {
				individualLettersOnPaper[indexOfLetterToOverwrite] = '@';
				indexOfLetterToWrite++;
			}
		}

		String newTextOnPaper = new String(individualLettersOnPaper);
		this.textOnPaper = newTextOnPaper;
	}

	private void replacePartOfTextOnPaperWithWhitespace(int startOfEraseChunk,
			int numberOfLettersToErase, int eraserDurability) {
		char[] individualLettersOnPaper = this.textOnPaper.toCharArray();
		int endOfEraseChunk = startOfEraseChunk + numberOfLettersToErase;

		for (int indexOfLetterToErase = endOfEraseChunk
				- 1; indexOfLetterToErase >= startOfEraseChunk
						&& eraserDurability > 0; indexOfLetterToErase--) {
			char characterOnPaper = individualLettersOnPaper[indexOfLetterToErase];
			boolean characterOnPaperIsNotWhitespace = !isWhitespace(characterOnPaper);

			if (characterOnPaperIsNotWhitespace) {
				eraserDurability -= 1;
			}

			individualLettersOnPaper[indexOfLetterToErase] = ' ';
		}
		String newTextOnPaper = new String(individualLettersOnPaper);
		this.textOnPaper = newTextOnPaper;
	}

	private boolean isWhitespace(char character) {
		return character == ' ' || character == '\n' || character == '\t';
	}

	@Override
	public String getTextOnPaper() {
		return this.textOnPaper;
	}

}
