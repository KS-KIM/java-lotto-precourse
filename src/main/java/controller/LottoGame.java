package controller;

import java.util.List;
import java.util.Objects;

import domain.MatchResult;
import domain.Profits;
import domain.lotto.Lotto;
import domain.lotto.LottoMachine;
import domain.lotto.LottoPurchaseMoney;
import domain.lotto.WinningLotto;
import view.InputView;
import view.OutputView;

/**
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-20
 */
public class LottoGame {
	private final LottoMachine lottoMachine;

	private List<Lotto> lottos;
	private WinningLotto winningLotto;
	private LottoPurchaseMoney lottoPurchaseMoney;

	public LottoGame(LottoMachine lottoMachine) {
		this.lottoMachine = Objects.requireNonNull(lottoMachine);
	}

	public void play() {
		initialize();
		match();
	}

	private void initialize() {
		initLottos();
		initWinningLotto();
	}

	private void initLottos() {
		lottoPurchaseMoney = InputView.inputLottoPurchaseMoney();
		lottos = lottoMachine.purchase(lottoPurchaseMoney);
		OutputView.showLottos(lottos);
	}

	private void initWinningLotto() {
		winningLotto = InputView.inputWinningLotto();
	}

	private void match() {
		MatchResult matchResult = new MatchResult(lottos, winningLotto);
		Profits profits = matchResult.calculateResult();
		long totalProfits = profits.calculateTotalProfits();
		long profitRate = lottoPurchaseMoney.calculateProfitRate(totalProfits);
		OutputView.showResult(profits, profitRate);
	}
}
