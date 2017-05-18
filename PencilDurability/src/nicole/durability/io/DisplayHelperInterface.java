package nicole.durability.io;

import java.util.*;

import nicole.durability.*;
import nicole.durability.actions.*;

public interface DisplayHelperInterface {

	void displayMainMenuWithUserPrompt(List<MenuAction> pencilActionsToDisplay);

	void displayPencilStats(PencilInterface pencil);

}
