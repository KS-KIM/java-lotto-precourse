package domain.lotto;

/**
 * 로또 구입금액
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-20
 */
public class LottoPurchaseMoney {
	private static final String MONEY_OUT_OF_RANGE_EXCEPTION = "잘못된 금액을 입력하셨습니다.";
	private static final String INVALID_UNIT_EXCEPTION = "잘못된 금액을 입력하셨습니다.";
	private static final int MIN_MONEY = 1_000;
	private static final int MAX_MONEY = 50_000;
	private static final int PURCHASE_UNIT = 1_000;

	private final int money;

	public LottoPurchaseMoney(int money) {
		validateMoney(money);
		this.money = money;
	}

	private void validateMoney(int money) {
		validateRange(money);
		validateUnit(money);
	}

	private void validateRange(int money) {
		if (money < MIN_MONEY || money > MAX_MONEY) {
			throw new IllegalArgumentException(MONEY_OUT_OF_RANGE_EXCEPTION);
		}
	}

	private void validateUnit(int money) {
		if (money % PURCHASE_UNIT != 0) {
			throw new IllegalArgumentException(INVALID_UNIT_EXCEPTION);
		}
	}

	public static LottoPurchaseMoney parse(String text) {
		return new LottoPurchaseMoney(Integer.parseInt(text));
	}

	public int calculatePurchaseCount() {
		return money / PURCHASE_UNIT;
	}

	public long calculateProfitRate(long totalProfits) {
		return (totalProfits - money) * 100 / money;
	}
}
