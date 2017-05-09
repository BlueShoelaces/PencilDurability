package nicole.durability.mocks;

import nicole.durability.actions.*;

public class MockPencilAction implements PencilAction {

	private String textToDisplay;

	@Override
	public String getTextToDisplay() {
		return this.textToDisplay;
	}

	public void setTextToDisplay(String textToDisplay) {
		this.textToDisplay = textToDisplay;
	}

}
