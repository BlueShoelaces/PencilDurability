package nicole.durability;

import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses({ LauncherTest.class, PaperTest.class, PencilTest.class,
		PencilWriterSingletonTest.class })
public class SuiteDurability {

}
