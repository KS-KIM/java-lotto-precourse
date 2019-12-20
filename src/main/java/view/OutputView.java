package view;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import domain.Profit;
import domain.Profits;
import domain.lotto.Lotto;

/**
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-20
 */
public class OutputView {
	private static final String SEPARATOR = "=================================================";
	private static final String TOTAL_PROFITS_MESSAGE = "최종 수익: ";

	// 묵시적 생성자 방지
	private OutputView() {}

	public static void showLottos(List<Lotto> lottos) {
		System.out.println(lottos.stream()
				.map(Lotto::toString)
				.collect(Collectors.joining("\n")));
	}

	public static void showResult(Profits profits, long totalProfits) {
		showProfits(profits);
		showSeparator();
		showTotalProfits(totalProfits);
	}

	private static void showProfits(Profits profits) {
		profits.getProfits().stream()
			.filter(Profit::isProfitable)
			.forEach(System.out::println);
	}

	private static void showSeparator() {
		System.out.println(SEPARATOR);
	}

	private static void showTotalProfits(long profits) {
		String totalProfits = new DecimalFormat("#.###").format(profits);
		System.out.println(TOTAL_PROFITS_MESSAGE + totalProfits);
	}
}
