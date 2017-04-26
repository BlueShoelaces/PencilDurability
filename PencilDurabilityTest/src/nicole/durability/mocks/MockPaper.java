package nicole.durability.mocks;

import nicole.durability.*;

public class MockPaper implements PaperInterface {

	private String textWrittenToPaper;

	@Override
	public void showPaper() {

	}

	@Override
	public void write(String textToWriteToPaper) {
		this.textWrittenToPaper = textToWriteToPaper;
	}

	public String getTextWrittenToPaper() {
		return this.textWrittenToPaper;
	}
}
