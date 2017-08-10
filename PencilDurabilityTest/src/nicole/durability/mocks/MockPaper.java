package nicole.durability.mocks;

import nicole.durability.*;

public class MockPaper implements PaperInterface {

	private String textPassedToWrite;
	private String textPassedToWriteInWhitespaceGap;
	private boolean showPaperWasCalled = false;
	private boolean replaceWithWhitespaceWasCalled = false;
	private String textPassedToReplaceWithWhitespace;
	private int eraserDurabilityPassedToReplaceWithWhitespace;

	@Override
	public void showPaper() {
		this.showPaperWasCalled = true;
	}

	@Override
	public void write(String textToWriteToPaper) {
		this.textPassedToWrite = textToWriteToPaper;
	}

	@Override
	public void writeInWhitespaceGap(String stringToWriteToPaper) {
		this.textPassedToWriteInWhitespaceGap = stringToWriteToPaper;
	}

	@Override
	public void replaceWithWhitespace(String textToErase, int eraserDurability) {
		this.textPassedToReplaceWithWhitespace = textToErase;
		this.eraserDurabilityPassedToReplaceWithWhitespace = eraserDurability;
		this.replaceWithWhitespaceWasCalled = true;
	}

	public String getTextPassedToWrite() {
		return this.textPassedToWrite;
	}

	public String getTextPassedToWriteInWhitespaceGap() {
		return this.textPassedToWriteInWhitespaceGap;
	}

	public boolean replaceWithWhitespaceWasCalled() {
		return this.replaceWithWhitespaceWasCalled;
	}

	public boolean showPaperWasCalled() {
		return this.showPaperWasCalled;
	}

	public String getTextPassedToReplaceWithWhitespace() {
		return this.textPassedToReplaceWithWhitespace;
	}

	public int getEraserDurabilityPassedToReplaceWithWhitespace() {
		return this.eraserDurabilityPassedToReplaceWithWhitespace;
	}
}
