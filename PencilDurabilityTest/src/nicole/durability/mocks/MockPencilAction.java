package nicole.durability.mocks;

import nicole.durability.actions.*;

public class MockPencilAction implements PencilAction {

	private String textToDisplay;
	private boolean performWasCalled = false;

	@Override
	public String getTextToDisplay() {
		return this.textToDisplay;
	}

	@Override
	public void perform() {
		this.performWasCalled = true;
	}

	public void setTextToDisplay(String textToDisplay) {
		this.textToDisplay = textToDisplay;
	}

	public boolean performWasCalled() {
		return this.performWasCalled;
	}

}
