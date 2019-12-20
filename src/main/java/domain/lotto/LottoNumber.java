package domain.lotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 로또 수 하나를 의미하는 객체
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-20
 */
public class LottoNumber implements Comparable<LottoNumber> {
	private static final String LOTTO_NUMBER_OUT_OF_RANGE_EXCEPTION = "숫자는 1~45 사이의 수로 구성되어야 합니다.";
	private static final int MIN_LOTTO_NUMBER = 1;
	private static final int MAX_LOTTO_NUMBER = 45;
	private static final Map<Integer, LottoNumber> LOTTO_NUMBERS = new HashMap<>();

	private final int number;

	static {
		LOTTO_NUMBERS.putAll(IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
				.boxed()
				.collect(Collectors.toMap(it -> it, LottoNumber::new))
		);
	}

	private LottoNumber(int number) {
		validateNumber(number);
		this.number = number;
	}

	public static LottoNumber parse(String number) {
		return of(Integer.parseInt(number));
	}

	public static LottoNumber of(int number) {
		validateNumber(number);
		return LOTTO_NUMBERS.get(number);
	}

	private static void validateNumber(int number) {
		if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
			throw new IllegalArgumentException(LOTTO_NUMBER_OUT_OF_RANGE_EXCEPTION);
		}
	}

	public static List<LottoNumber> values() {
		return new ArrayList<>(LOTTO_NUMBERS.values());
	}

	@Override
	public String toString() {
		return String.valueOf(number);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNumber that = (LottoNumber)o;
		return number == that.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public int compareTo(LottoNumber that) {
		return Integer.compare(number, that.number);
	}
}
