package nicole.durability.mocks;

import nicole.durability.*;

public class MockPencil implements PencilInterface {

	private String textWrittenToPaper;
	private PaperInterface paperWrittenTo;
	private int currentDurability = 50;
	private int currentLength = 1;
	private PaperInterface paperErasedFrom;
	private String textErasedFromPaper;
	private boolean sharpenWasCalled = false;

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

	@Override
	public void sharpen() {
		this.sharpenWasCalled = true;
	}

	@Override
	public int getPencilLength() {
		return this.currentLength;
	}

	@Override
	public int getCurrentPencilDurability() {
		return this.currentDurability;
	}

	@Override
	public int getEraserDurability() {
		// TODO Auto-generated method stub
		return 0;
	}

	public PaperInterface getPaperWrittenTo() {
		return this.paperWrittenTo;
	}

	public String getTextWrittenToPaper() {
		return this.textWrittenToPaper;
	}

	public void setCurrentDurability(int currentDurability) {
		this.currentDurability = currentDurability;
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

	public boolean sharpenWasCalled() {
		return this.sharpenWasCalled;
	}
}
