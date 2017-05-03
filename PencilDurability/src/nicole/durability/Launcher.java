package nicole.durability;

public class Launcher {

	private static final int PENCIL_LENGTH_4 = 4;
	private static final int DURABILITY_500 = 500;

	public static void main(String[] args) {
		PencilWriterSingletonInterface pencilWriter = PencilWriterSingleton.instance();
		pencilWriter.run(new Paper(), new Pencil(DURABILITY_500, PENCIL_LENGTH_4));
	}

}
