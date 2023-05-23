package allAsserts;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class AllAssertsTest {

	@Test
	void testAsserts() {
		// Boolean Assertions
		assertTrue(true);
		// With supplied failure message
		assertFalse(false, "assertFalse failed");

		// Null Assertions
		assertNull(null);
		assertNotNull(new Object());

		// Equality Assertions
		assertEquals("Eins", "Eins");
		assertNotEquals("Eins", "Zwei");
		assertArrayEquals(new int[] { 1, 2, 3 }, new int[] { 1, 2, 3 });

		// Iterable Assertions
		List<String> expectedList = Arrays.asList("foo", "bar", "baz");
		List<String> actualList = Arrays.asList("foo", "bar", "baz");
		assertIterableEquals(expectedList, actualList);

		// String Assertions
		String expectedString = "Hello World";
		String actualString = "Hello World";
		assertEquals(expectedString, actualString);
		assertNotEquals("Hello", "World");
		assertLinesMatch(Arrays.asList("Hello", "World"), Arrays.asList("Hello", "World"));

		// Object Identity Assertions
		Object obj1 = new Object();
		Object obj2 = new Object();
		assertSame(obj1, obj1);
		assertNotSame(obj1, obj2);

		// Type Assertions
		assertInstanceOf(String.class, "Hello");

		// Timeout Assertions
		assertTimeout(java.time.Duration.ofSeconds(1), () -> {
			// Simulate time-consuming task
			Thread.sleep(500);
		});

		assertTimeoutPreemptively(java.time.Duration.ofSeconds(1), () -> {
			// Simulate time-consuming task
			Thread.sleep(500);
		});

		// Exception Assertions
		assertThrows(Exception.class, () -> {
			throw new IllegalArgumentException("Exception");
		});

		assertDoesNotThrow(() -> {
			// Code that should not throw any exception
		});

		assertThrowsExactly(IllegalArgumentException.class, () -> {
			throw new IllegalArgumentException("IllegalArgumentException");
		});

		// Grouped Assertions
		assertAll("Grouped assertions", //
				() -> assertEquals(2, 1 + 1), //
				() -> assertTrue(10 > 5));
	}
}
