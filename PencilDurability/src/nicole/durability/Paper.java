package nicole.durability;

public class Paper implements PaperInterface {

	private String textOnPaper = "";

	@Override
	public void write(String stringToWriteToPaper) {
		this.textOnPaper += stringToWriteToPaper + "\n";
	}

	@Override
	public void showPaper() {
		System.out.print("\n" + this.textOnPaper + "\n");
	}

	@Override
	public void replaceWithWhitespace(String textToErase) {
		int indexOfLastOccurenceOfTextToErase = this.textOnPaper.lastIndexOf(textToErase);
		int numberOfLettersToErase = textToErase.length();

		replacePartOfTextOnPaperWithWhitespace(indexOfLastOccurenceOfTextToErase,
				numberOfLettersToErase);
	}

	private void replacePartOfTextOnPaperWithWhitespace(int index, int numberOfLettersToErase) {
		char[] individualLettersOnPaper = this.textOnPaper.toCharArray();
		int pointToEraseTo = index + numberOfLettersToErase;

		for (int indexOfLetterToErase = index; indexOfLetterToErase < pointToEraseTo; indexOfLetterToErase++) {
			individualLettersOnPaper[indexOfLetterToErase] = ' ';
		}
		String newTextOnPaper = new String(individualLettersOnPaper);
		this.textOnPaper = newTextOnPaper;
	}

	public String getTextOnPaper() {
		return this.textOnPaper;
	}

}
