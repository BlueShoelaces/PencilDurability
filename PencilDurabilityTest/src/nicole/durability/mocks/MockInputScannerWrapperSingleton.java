package nicole.durability.mocks;

import java.io.*;

import nicole.durability.io.*;

public class MockInputScannerWrapperSingleton implements InputScannerWrapperSingletonInterface {

	private boolean nextLineWasCalled = false;
	private String textReturnedFromNextLine = "default text";;

	InputScannerWrapperSingletonInterface instance() {
		return null;
	}

	@Override
	public String nextLine() {
		this.nextLineWasCalled = true;
		return this.textReturnedFromNextLine;
	}

	@Override
	public InputStream getInputStream() {
		return null;
	}

	public boolean nextLineWasCalled() {
		return this.nextLineWasCalled;
	}

	public String getTextReturnedFromNextLine() {
		return this.textReturnedFromNextLine;
	}

	public void setTextReturnedFromNextLine(String textReturnedFromNextLine) {
		this.textReturnedFromNextLine = textReturnedFromNextLine;
	}

}
