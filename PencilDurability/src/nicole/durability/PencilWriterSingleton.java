package nicole.durability;

public class PencilWriterSingleton implements PencilWriterSingletonInterface {

	private static final String DEFAULT_TEXT = "Play it, Sam. Play 'As Time Goes By.'\n";
	private static PencilWriterSingletonInterface instance;

	private PencilWriterSingleton() {

	}

	public static PencilWriterSingletonInterface instance() {
		if (instance == null) {
			instance = new PencilWriterSingleton();
		}
		return instance;
	}

	@Override
	public void run(PaperInterface paper, PencilInterface pencil) {
		pencil.writeOnPaper(paper, DEFAULT_TEXT);
	}

}
