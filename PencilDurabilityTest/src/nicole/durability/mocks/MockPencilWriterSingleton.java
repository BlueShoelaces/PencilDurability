package nicole.durability.mocks;

import nicole.durability.*;

public class MockPencilWriterSingleton implements PencilWriterSingletonInterface {

	private boolean runWasCalled = false;
	private PencilInterface pencilPassedToRun;

	@Override
	public void run(PaperInterface paper, PencilInterface pencil) {
		this.runWasCalled = true;
		this.pencilPassedToRun = pencil;
	}

	public boolean runWasCalled() {
		return this.runWasCalled;
	}

	public PencilInterface getPencilPassedToRun() {
		return this.pencilPassedToRun;
	}

}
