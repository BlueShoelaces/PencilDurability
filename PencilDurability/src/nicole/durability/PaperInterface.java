package nicole.durability;

public interface PaperInterface {

	void showPaper();

	void write(String stringToWriteToPaper);

	void writeInWhitespaceGap(String stringToWriteToPaper);

	boolean replaceWithWhitespace(String textToErase, int eraserDurability);

	String getTextOnPaper();

}
