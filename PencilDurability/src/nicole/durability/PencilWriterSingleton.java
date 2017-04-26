package nicole.durability;

public class PencilWriterSingleton extends Launcher {

	private static PencilWriterSingleton instance;

	private PencilWriterSingleton() {

	}

	public static PencilWriterSingleton instance() {
		if (instance == null) {
			instance = new PencilWriterSingleton();
		}
		return instance;
	}

}
