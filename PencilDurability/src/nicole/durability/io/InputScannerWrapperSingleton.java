package nicole.durability.io;

import java.io.*;

public class InputScannerWrapperSingleton {

	private static InputScannerWrapperSingleton instance;
	private static InputStream inputStream = System.in;

	private InputScannerWrapperSingleton() {

	}

	public static InputScannerWrapperSingleton instance() {
		if (instance == null) {
			instance = new InputScannerWrapperSingleton();
		}
		return instance;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

}
