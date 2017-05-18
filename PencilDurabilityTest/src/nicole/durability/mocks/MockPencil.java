package nicole.durability.mocks;

import nicole.durability.*;

public class MockPencil implements PencilInterface {

	private String textWrittenToPaper;
	private PaperInterface paperWrittenTo;
	private int currentDurability = 50;
	private int currentLength = 1;

	@Override
	public void writeOnPaper(PaperInterface paper, String textToWrite) {
		this.textWrittenToPaper = textToWrite;
		this.paperWrittenTo = paper;
	}

	public PaperInterface getPaperWrittenTo() {
		return this.paperWrittenTo;
	}

	public String getTextWrittenToPaper() {
		return this.textWrittenToPaper;
	}

	@Override
	public int getCurrentDurability() {
		return this.currentDurability;
	}

	public void setCurrentDurability(int currentDurability) {
		this.currentDurability = currentDurability;
	}

	@Override
	public int getPencilLength() {
		return this.currentLength;
	}

	public void setCurrentLength(int currentLength) {
		this.currentLength = currentLength;
	}

}
