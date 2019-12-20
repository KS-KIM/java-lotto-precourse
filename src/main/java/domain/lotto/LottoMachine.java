package domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 돈을 입력받아 랜덤한 로또 수를 만들어주는 기계
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-20
 */
public class LottoMachine {
	private final List<LottoNumber> numbers = LottoNumber.values();

	public LottoMachine() {}

	public List<Lotto> purchase(LottoPurchaseMoney lottoPurchaseMoney) {
		return Stream.generate(this::purchase)
				.limit(lottoPurchaseMoney.calculatePurchaseCount())
				.collect(Collectors.toList());
	}

	private Lotto purchase() {
		shuffle();
		return new Lotto(new ArrayList<>(numbers.subList(0, Lotto.TOTAL_NUMBER_COUNT)));
	}

	private void shuffle() {
		Collections.shuffle(numbers);
	}
}
