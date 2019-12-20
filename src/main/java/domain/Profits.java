package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-20
 */
public class Profits {
	private final List<Profit> profits;

	public Profits(List<Profit> profits) {
		Collections.sort(profits);
		this.profits = Objects.requireNonNull(profits);
	}

	public static Profits of(Map<Rank, Long> rankCounts) {
		List<Profit> profits = new ArrayList<>();
		for (Map.Entry<Rank, Long> rank: rankCounts.entrySet()) {
			Profit profit = new Profit(rank.getKey(), Math.toIntExact(rank.getValue()));
			profits.add(profit);
		}
		return new Profits(profits);
	}

	public List<Profit> getProfits() {
		return Collections.unmodifiableList(profits);
	}

	public long calculateTotalProfits() {
		return profits.stream()
				.mapToLong(Profit::calculateWinningMoney)
				.sum();
	}
}
