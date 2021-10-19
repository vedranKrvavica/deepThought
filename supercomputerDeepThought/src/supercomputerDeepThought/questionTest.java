package supercomputerDeepThought;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class questionTest {

	@Test
	public void test() {
		assertEquals("test?", DeepThought.checkQuestion("test?"));
		assertEquals(null, DeepThought.checkQuestion("?"));
		assertEquals(null, DeepThought.checkQuestion("   ?"));
		String qCorrect = "a".repeat(254) + "?";
		assertEquals(qCorrect, DeepThought.checkQuestion(qCorrect));
		String qWrong = "a".repeat(255) + "?";
		assertEquals(null, DeepThought.checkQuestion(qWrong));
	}

}
