package nicole.durability.mocks;

import nicole.durability.*;

public class MockPaper implements PaperInterface {

	private String textWrittenToPaper;
	private boolean showPaperWasCalled = false;

	@Override
	public void showPaper() {
		this.showPaperWasCalled = true;
	}

	@Override
	public void write(String textToWriteToPaper) {
		this.textWrittenToPaper = textToWriteToPaper;
	}

	public String getTextWrittenToPaper() {
		return this.textWrittenToPaper;
	}

	public boolean showPaperWasCalled() {
		return this.showPaperWasCalled;
	}
}
