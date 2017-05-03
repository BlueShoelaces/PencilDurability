package nicole.durability.mocks;

import nicole.durability.*;

public class MockPaper implements PaperInterface {

	private boolean showPaperWasCalled = false;
	private boolean replaceWithWhitespaceWasCalled = false;
	private String textWrittenToPaper;
	private String textPassedToReplaceWithWhitespace;

	@Override
	public void showPaper() {
		this.showPaperWasCalled = true;
	}

	@Override
	public void write(String textToWriteToPaper) {
		this.textWrittenToPaper = textToWriteToPaper;
	}

	@Override
	public void replaceWithWhitespace(String textToErase) {
		this.textPassedToReplaceWithWhitespace = textToErase;
		this.replaceWithWhitespaceWasCalled = true;
	}

	public String getTextWrittenToPaper() {
		return this.textWrittenToPaper;
	}

	public void setTextWrittenToPaper(String textWrittenToPaper) {
		this.textWrittenToPaper = textWrittenToPaper;
	}

	public boolean showPaperWasCalled() {
		return this.showPaperWasCalled;
	}

	public boolean replaceWithWhitespaceWasCalled() {
		return this.replaceWithWhitespaceWasCalled;
	}

	public String getTextPassedToReplaceWithWhitespace() {
		return this.textPassedToReplaceWithWhitespace;
	}
}
