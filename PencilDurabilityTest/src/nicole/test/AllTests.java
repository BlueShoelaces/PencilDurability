package nicole.test;

import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

import nicole.durability.*;

@RunWith(Suite.class)
@SuiteClasses({ PaperTest.class, PencilWriterSingletonTest.class, PencilTest.class })
public class AllTests {

}
