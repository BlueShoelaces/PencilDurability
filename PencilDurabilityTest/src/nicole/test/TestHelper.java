package nicole.test;

import static org.junit.Assert.*;

public class TestHelper {

	protected void assertImplementsInterface(Class<? extends Object> concreteClass,
			Class<? extends Object> expectedInterface) {
		Class<?>[] interfaces = concreteClass.getInterfaces();
		assertEquals(1, interfaces.length);

		Class<?> actualInterface = interfaces[0];
		assertSame(expectedInterface, actualInterface);
	}

	protected <T> T assertIsOfTypeAndGet(Class<T> type, Object object) {
		assertNotNull("Expected " + type + " but was null", object);
		assertTrue("Expected " + type + " but was " + object.getClass(),
				type.isAssignableFrom(object.getClass()));
		return type.cast(object);
	}

	protected void assertFail(String failureMessage) {
		assertTrue(failureMessage, false);
	}

	protected void assertFalse(boolean condition) {
		assertTrue(!condition);
	}
}
