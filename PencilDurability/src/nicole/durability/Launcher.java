package nicole.durability;

public class Launcher extends Pencil {

	public static void main(String[] args) {
		PencilWriterSingleton.instance().run();
	}

}
