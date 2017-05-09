package nicole.test;

import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

import nicole.durability.*;
import nicole.durability.actions.*;
import nicole.durability.io.*;

@RunWith(Suite.class)
@SuiteClasses({ InputScannerWrapperSingletonTest.class, LauncherTest.class, PaperTest.class,
		PencilWriterActionMenuTest.class, PencilWriterSingletonTest.class, PencilTest.class,
		WritePencilActionTest.class })
public class AllTests {

}
