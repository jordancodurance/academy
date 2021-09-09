import com.codurance.BowlingGameScoreCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BowlingGameScoreCalculatorShould {

	private BowlingGameScoreCalculator target;

	@BeforeEach
	void setUp() {
		target = new BowlingGameScoreCalculator();
	}

	@ParameterizedTest
	@CsvSource({
			"9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||, 90",
			"5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5, 150",
			"X|X|X|X|X|X|X|X|X|X||XX, 300",
			"X|7/|9-|X|-8|8/|-6|X|X|X||81, 167"
	})
	public void calculate_score_of_game(String game, int expectScore) {
		assertEquals(target.calculateScore(game), expectScore);
	}

}
