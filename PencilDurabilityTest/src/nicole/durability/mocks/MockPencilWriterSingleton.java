package nicole.durability.mocks;

import nicole.durability.*;

public class MockPencilWriterSingleton implements PencilWriterSingletonInterface {

	private boolean runWasCalled = false;

	@Override
	public void run() {
		this.runWasCalled = true;
	}

	public boolean runWasCalled() {
		return this.runWasCalled;
	}

}
