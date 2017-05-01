package nicole.durability.mocks;

import nicole.durability.*;

public class MockPencil implements PencilInterface {

	private String textWrittenToPaper;
	private PaperInterface paperWrittenTo;

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
	public int getDurability() {
		return 0;
	}

}
