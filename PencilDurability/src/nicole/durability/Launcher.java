package nicole.durability;

public class Launcher {

	public static void main(String[] args) {
		PencilWriterSingletonInterface pencilWriter = PencilWriterSingleton.instance();
		pencilWriter.run(new Paper(), new Pencil());
	}

}
