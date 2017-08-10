package nicole.durability;

public interface PencilInterface {

	void writeOnPaper(PaperInterface paper, String textToWrite);

	void writeOnPaperInWhitespaceGap(PaperInterface paper, String textToWrite);

	int getCurrentPencilDurability();

	int getPencilLength();

	void erase(PaperInterface paper, String textToErase);

	void sharpen();

	int getEraserDurability();

}
