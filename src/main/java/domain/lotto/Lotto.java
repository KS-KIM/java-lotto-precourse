package domain.lotto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 로또 한장을 의미하는 객체
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-20
 */
public class Lotto {
	public static final int TOTAL_NUMBER_COUNT = 6;
	private static final String NUMBER_OUT_OF_SIZE_EXCEPTION = "로또 숫자의 개수가 올바르지 않습니다";
	private static final String NUMBER_DELIMITER = ",";

	private final List<LottoNumber> numbers;

	public Lotto(List<LottoNumber> numbers) {
		validateNumber(numbers);
		Collections.sort(numbers);
		this.numbers = numbers;
	}

	private void validateNumber(List<LottoNumber> numbers) {
		Objects.requireNonNull(numbers);
		validateNumberSize(numbers);
	}

	private void validateNumberSize(List<LottoNumber> numbers) {
		if (numbers.size() != TOTAL_NUMBER_COUNT) {
			throw new IllegalArgumentException(NUMBER_OUT_OF_SIZE_EXCEPTION);
		}
	}

	public static Lotto parse(String text) {
		return new Lotto(Stream.of(text.split(NUMBER_DELIMITER))
				.map(String::trim)
				.map(Integer::parseInt)
				.map(LottoNumber::of)
				.collect(Collectors.toList()));
	}

	public int countMatchNumbersWith(Lotto that) {
		return (int) numbers.stream()
				.filter(that::contains)
				.count();
	}

	public boolean contains(LottoNumber lottoNumber) {
		return numbers.contains(lottoNumber);
	}

	@Override
	public String toString() {
		return numbers.toString();
	}
}
