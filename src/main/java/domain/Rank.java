package domain;

import java.util.stream.Stream;

/**
 * 로또 등수를 의미하는 enum
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-20
 */
public enum Rank {
	FIRST(6, 2_000_000_000), // 1등
	SECOND(5, 30_000_000), // 2등
	THIRD(5, 1_500_000), // 3등
	FOURTH(4, 50_000), // 4등
	FIFTH(3, 5_000), // 5등
	MISS(0, 0);

	private static final int WINNING_MIN_COUNT = 3;

	private int countOfMatch;
	private int winningMoney;

	Rank(int countOfMatch, int winningMoney) {
		this.countOfMatch = countOfMatch;
		this.winningMoney = winningMoney;
	}

	public int getCountOfMatch() {
		return countOfMatch;
	}

	public int getWinningMoney() {
		return winningMoney;
	}

	public int calculateWinningMonies(int countOfMatch) {
		return winningMoney * countOfMatch;
	}

	public static Rank valueOf(int countOfMatch, boolean matchBonus) {
		if (countOfMatch < WINNING_MIN_COUNT) {
			return MISS;
		}

		if (SECOND.matchCount(countOfMatch) && matchBonus) {
			return SECOND;
		}

		return Stream.of(Rank.values())
				.filter(it -> it.matchCount(countOfMatch))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException(countOfMatch + "는 유효하지 않은 값입니다."));
	}

	private boolean matchCount(int countOfMatch) {
		return this.countOfMatch == countOfMatch;
	}
}

