package nicole.durability.mocks;

import nicole.durability.actions.*;

public class MockMenuAction implements MenuAction {

	private String textToDisplay;
	private boolean performWasCalled = false;

	@Override
	public String getMenuTextToDisplay() {
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
