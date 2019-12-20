package domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import domain.lotto.Lotto;
import domain.lotto.WinningLotto;

/**
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-20
 */
public class MatchResult {
	private final List<Lotto> lottos;
	private final WinningLotto winningLotto;

	public MatchResult(List<Lotto> lottos, WinningLotto winningLotto) {
		this.lottos = Objects.requireNonNull(lottos);
		this.winningLotto = Objects.requireNonNull(winningLotto);
	}

	public Profits calculateResult() {
		Map<Rank, Long> rankCount = lottos.stream()
				.map(winningLotto::match)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return Profits.of(rankCount);
	}
}
