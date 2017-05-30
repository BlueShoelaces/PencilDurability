package nicole.durability;

public interface PencilInterface {

	void writeOnPaper(PaperInterface paper, String textToWrite);

	int getCurrentDurability();

	int getPencilLength();

	void erase(PaperInterface paper, String textToErase);

}
