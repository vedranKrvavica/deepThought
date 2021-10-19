package supercomputerDeepThought;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class getAnswerTest {

	@Test
	public void test() {
		DeepThought dt = new DeepThought();
		dt.initializeExitStrategy();
		assertEquals(dt.answerToEverything, dt.getAnswers("why?"));
		assertEquals(dt.exitArray, dt.getAnswers("How can I leave?"));
		assertEquals(dt.exitArray, dt.getAnswers("How do I quit?"));
		assertEquals(dt.answerToEverything, dt.getAnswers("Come over here!"));
	}

}
