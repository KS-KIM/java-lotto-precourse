package view;

import java.util.Scanner;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import domain.lotto.LottoPurchaseMoney;
import domain.lotto.WinningLotto;

/**
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-20
 */
public class InputView {
	private static final String LOTTO_PURCHASE_MONEY_INPUT_GUIDE_MESSAGE =
			"구입하실 금액을 입력해주세요. (최소 구입금액: 1,000원, 최대 구입금액: 50,000원, 단위: 1,000원)";
	private static final String INVALID_INPUT_MESSAGE = "잘못된 값을 입력하셨습니다. 다시 입력해주세요.";
	private static final String WINNING_LOTTO_INPUT_GUIDE_MESSAGE = "우승 로또 번호를 입력해주세요";
	private static final String BONUS_NO_INPUT_GUIDE_MESSAGE = "보너스 번호를 입력하세요";
	private static final Scanner SCANNER = new Scanner(System.in);

	// 묵시적 생성자 방지
	private InputView() {}

	public static LottoPurchaseMoney inputLottoPurchaseMoney() {
		try {
			System.out.println(LOTTO_PURCHASE_MONEY_INPUT_GUIDE_MESSAGE);
			String text = SCANNER.nextLine();
			return LottoPurchaseMoney.parse(text);
		} catch (IllegalArgumentException e) {
			System.out.println(INVALID_INPUT_MESSAGE);
			return inputLottoPurchaseMoney();
		}
	}

	public static WinningLotto inputWinningLotto() {
		try {
			Lotto lotto = inputLottoNumber();
			LottoNumber bonusNo = inputBonusNo();
			return new WinningLotto(lotto, bonusNo);
		} catch (IllegalArgumentException e) {
			System.out.println(INVALID_INPUT_MESSAGE);
			return inputWinningLotto();
		}
	}

	public static Lotto inputLottoNumber() {
		try {
			System.out.println(WINNING_LOTTO_INPUT_GUIDE_MESSAGE);
			String text = SCANNER.nextLine();
			return Lotto.parse(text);
		} catch (IllegalArgumentException e) {
			System.out.println(INVALID_INPUT_MESSAGE);
			return inputLottoNumber();
		}
	}

	public static LottoNumber inputBonusNo() {
		try {
			System.out.println(BONUS_NO_INPUT_GUIDE_MESSAGE);
			String text = SCANNER.nextLine();
			return LottoNumber.parse(text);
		} catch (IllegalArgumentException e) {
			System.out.println(INVALID_INPUT_MESSAGE);
			return inputBonusNo();
		}
	}
}
