import controller.LottoGame;
import domain.lotto.LottoMachine;

/**
 * 로또 게임을 실행하는 시작부
 *
 * @version 1.0.0
 * @author KSKIM
 * @since 2019-12-20
 */
public class LottoApplication {
	public static void main(String[] args) {
		LottoMachine lottoMachine = new LottoMachine();
		LottoGame lottoGame = new LottoGame(lottoMachine);
		lottoGame.play();
	}
}
