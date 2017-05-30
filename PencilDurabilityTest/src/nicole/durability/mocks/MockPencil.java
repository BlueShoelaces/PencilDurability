package nicole.durability.mocks;

import nicole.durability.*;

public class MockPencil implements PencilInterface {

	private String textWrittenToPaper;
	private PaperInterface paperWrittenTo;
	private int currentDurability = 50;
	private int currentLength = 1;
	private PaperInterface paperErasedFrom;
	private String textErasedFromPaper;

	@Override
	public void writeOnPaper(PaperInterface paper, String textToWrite) {
		this.textWrittenToPaper = textToWrite;
		this.paperWrittenTo = paper;
	}

	@Override
	public void erase(PaperInterface paper, String textToErase) {
		this.paperErasedFrom = paper;
		this.textErasedFromPaper = textToErase;
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

	public PaperInterface getPaperErasedFrom() {
		return this.paperErasedFrom;
	}

	public String getTextErasedFromPaper() {
		return this.textErasedFromPaper;
	}
}
