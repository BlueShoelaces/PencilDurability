package nicole.durability.io;

public class InputScannerWrapperSingleton {

	private static InputScannerWrapperSingleton instance;

	private InputScannerWrapperSingleton() {

	}

	public static InputScannerWrapperSingleton instance() {
		if (instance == null) {
			instance = new InputScannerWrapperSingleton();
		}
		return instance;
	}

}
