package nicole.durability;

public class Paper {

	private String textOnPaper = "";

	public void write(String stringToWriteToPaper) {
		this.textOnPaper += stringToWriteToPaper;
	}

	public void showPaper() {
		System.out.print(this.textOnPaper);
	}

}
