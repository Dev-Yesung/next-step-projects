package study;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class StringTest {

	@Test
	void replace() {
		String actual = "abc".replace("b", "d");
		assertThat(actual).isEqualTo("adc");
	}

	/**
	 * 요구사항 1<br>
	 * 1,2"을 ,로 split 했을 때 1과 2로 잘 분리되는지 확인하는 학습 테스트를 구현한다.<br>
	 * "1"을 ,로 split 했을 때 1만을 포함하는 배열이 반환되는지에 대한 학습 테스트를 구현한다.<br>
	 */
	@DisplayName("'1,2'를 ,로 split 했을 때 1과 2로 분리 성공")
	@Test
	void splitStringTest1() {
		// given
		var origin = "1,2";

		// when
		String[] tokens = origin.split(",");

		// then
		assertThat(tokens.length).isEqualTo(2);
		assertThat(tokens).contains("1", "2");
	}

	@DisplayName("'1'를 ,로 split 했을 때 1만 포함")
	@Test
	void splitStringTest2() {
		// given
		var origin = "1";

		// when
		String[] tokens = origin.split(",");

		// then
		assertThat(tokens.length).isEqualTo(1);
		assertThat(tokens).containsExactly("1");
	}

	/**
	 * 요구사항 2<br>
	 * "(1,2)" 값이 주어졌을 때<br>
	 * String 의 substring() 메소드를 활용해 ()을 제거하고<br>
	 * "1,2"를 반환하도록 구현한다.<br>
	 */
	@DisplayName("'(1,2)'에서 ()를 제거하여 '1,2'만 반환 성공")
	@Test
	void substringTest1() {
		// given
		var origin = "(1,2)";

		// when
		String strippedRightParenthesis = origin.substring(0, origin.length() - 1);
		String result = strippedRightParenthesis.substring(1);

		// then
		assertThat(result).isEqualTo("1,2");
	}

	/**
	 * 요구사항 3<br>
	 * "abc" 값이 주어졌을 때 String 의 charAt() 메소드를 활용해 특정 위치의 문자를 가져오는 학습 테스트를 구현한다.<br>
	 * String 의 charAt() 메소드를 활용해 특정 위치의 문자를 가져올 때 위치 값을 벗어나면 StringIndexOutOfBoundsException 이 발생하는 부분에 대한 학습 테스트를 구현한다.<br>
	 * JUnit 의 @DisplayName 을 활용해 테스트 메소드의 의도를 드러낸다.<br>
	 */
	@DisplayName("'abc' 값이 주어졌을 때 String 의 charAt() 메소드를 활용해 특정 문자를 가져옴")
	@CsvSource(value = "0:a, 1:b, 2:c", delimiter = ':')
	@ParameterizedTest
	void charAtTest1(String position, String value) {
		// given
		var origin = "abc";
		var index = Integer.parseInt(position);
		var expected = value.charAt(0);

		// when
		char token = origin.charAt(index);

		// then
		assertThat(token).isEqualTo(expected);
	}

	@DisplayName("문자열에서 유효한 위치 값을 벗어나면 예외발생")
	@ValueSource(strings = {"", "1", "12", "123", "1234", "12345"})
	@ParameterizedTest
	void charAtTest2(String value) {
		// when -> then
		assertThatThrownBy(() -> value.charAt(value.length()))
			.isInstanceOf(StringIndexOutOfBoundsException.class);
	}
}
