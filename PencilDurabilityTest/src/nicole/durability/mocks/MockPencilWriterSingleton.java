package nicole.durability.mocks;

import nicole.durability.*;
import nicole.durability.io.*;

public class MockPencilWriterSingleton implements PencilWriterSingletonInterface {

	private boolean runWasCalled = false;
	private PencilWriterActionMenuInterface menuPassedToRun;

	@Override
	public void run(PencilWriterActionMenuInterface pencilWriterActionMenu) {
		this.menuPassedToRun = pencilWriterActionMenu;
		this.runWasCalled = true;
	}

	public boolean runWasCalled() {
		return this.runWasCalled;
	}

	public PencilWriterActionMenuInterface getMenuPassedToRun() {
		return this.menuPassedToRun;
	}

}
