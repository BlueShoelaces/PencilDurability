package nicole.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class TestHelper {

	protected void assertImplementsInterface(Class<? extends Object> concreteClass,
			Class<? extends Object> expectedInterface) {
		Class<?>[] interfaces = concreteClass.getInterfaces();
		assertEquals(1, interfaces.length);

		Class<?> actualInterface = interfaces[0];
		assertSame(expectedInterface, actualInterface);
	}
}
