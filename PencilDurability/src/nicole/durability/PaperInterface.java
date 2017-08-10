package nicole.durability;

public interface PaperInterface {

	void showPaper();

	void write(String stringToWriteToPaper);

	void writeInWhitespaceGap(String stringToWriteToPaper);

	void replaceWithWhitespace(String textToErase, int eraserDurability);

	String getTextOnPaper();

}
