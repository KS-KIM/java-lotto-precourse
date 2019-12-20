package domain;

import java.text.DecimalFormat;

/**
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-20
 */
public class Profit implements Comparable<Profit> {
	private final Rank rank;
	private final int matchCount;

	public Profit(Rank rank, int matchCount) {
		this.rank = rank;
		this.matchCount = matchCount;
	}

	@Override
	public String toString() {
		DecimalFormat decimalFormat = new DecimalFormat("#.###");
		String winningMoney = decimalFormat.format(calculateWinningMoney());
		return String.format("순위: %s, 맞춘 개수: %s, 이율: %s", rank, matchCount, winningMoney);
	}

	public long calculateWinningMoney() {
		return rank.calculateWinningMonies(matchCount);
	}

	public boolean isProfitable() {
		return !Rank.MISS.equals(rank);
	}

	@Override
	public int compareTo(Profit that) {
		return rank.compareTo(that.rank);
	}
}
