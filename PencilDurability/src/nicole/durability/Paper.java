package nicole.durability;

public class Paper implements PaperInterface {

	private String textOnPaper = "";

	@Override
	public void write(String stringToWriteToPaper) {
		this.textOnPaper += stringToWriteToPaper;
	}

	@Override
	public void showPaper() {
		System.out.print(this.textOnPaper);
	}

	@Override
	public void replaceWithWhitespace(String textToErase) {

	}

}
