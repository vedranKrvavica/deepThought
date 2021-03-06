package supercomputerDeepThought;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Test;

public class answerTest {

	@Test
	public void test() {
		assertArrayEquals(null, DeepThought.checkAnswers("test?"));
		assertArrayEquals(new String[]{"a"}, DeepThought.checkAnswers(" \"a\""));
		assertArrayEquals(new String[]{"a", "b"}, DeepThought.checkAnswers(" \"a\" \"b\""));
		assertArrayEquals(null, DeepThought.checkAnswers(" \"a\"  \"b\""));
		assertArrayEquals(null, DeepThought.checkAnswers(""));
		String aRepeat = " \"a\"".repeat(500);
		assertArrayEquals(new String[]{"a"}, DeepThought.checkAnswers(aRepeat));
		assertArrayEquals(new String[]{"a", "b"}, DeepThought.checkAnswers(aRepeat + " \"b\""));
		String aWrong = " \"" + "a".repeat(256) + "\"";
		String aRight = " \"" +"a".repeat(255) + "\"";
		assertArrayEquals(null, DeepThought.checkAnswers(aWrong));
		assertArrayEquals(new String[]{"a".repeat(255)}, DeepThought.checkAnswers(aRight));
	}

}
