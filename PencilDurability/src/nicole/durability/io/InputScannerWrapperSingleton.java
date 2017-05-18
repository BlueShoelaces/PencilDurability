package nicole.durability.io;

import java.io.*;
import java.util.*;

public class InputScannerWrapperSingleton implements InputScannerWrapperSingletonInterface {

	private static InputScannerWrapperSingletonInterface instance;
	private static InputStream inputStream = System.in;
	private Scanner scanner;

	private InputScannerWrapperSingleton() {
		this.scanner = new Scanner(System.in);
	}

	public static InputScannerWrapperSingletonInterface instance() {
		if (instance == null) {
			instance = new InputScannerWrapperSingleton();
		}
		return instance;
	}

	@Override
	public InputStream getInputStream() {
		return inputStream;
	}

	@Override
	public String nextLine() {
		String nextLineFromScanner = this.scanner.nextLine();
		return nextLineFromScanner;
	}

}
