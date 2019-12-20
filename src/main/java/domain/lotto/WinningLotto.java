package domain.lotto;

import java.util.Objects;

import domain.Rank;

/**
 * 당첨 번호를 담당하는 객체
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-20
 */
public class WinningLotto {
	private final Lotto lotto;
	private final LottoNumber bonusNo;

	public WinningLotto(Lotto lotto, LottoNumber bonusNo) {
		validateWinningLotto(lotto, bonusNo);
		this.lotto = lotto;
		this.bonusNo = bonusNo;
	}

	private void validateWinningLotto(Lotto lotto, LottoNumber bonusNo) {
		Objects.requireNonNull(lotto);
		Objects.requireNonNull(bonusNo);
		validateDuplicateBonusNo(lotto, bonusNo);
	}

	private void validateDuplicateBonusNo(Lotto lotto, LottoNumber bonusNo) {
		if (lotto.contains(bonusNo)) {
			throw new IllegalArgumentException("보너스 번호가 로또 번호와 일치합니다.");
		}
	}

	public Rank match(Lotto userLotto) {
		int matchCount = lotto.countMatchNumbersWith(userLotto);
		boolean matchBonus = userLotto.contains(bonusNo);
		return Rank.valueOf(matchCount, matchBonus);
	}
}
