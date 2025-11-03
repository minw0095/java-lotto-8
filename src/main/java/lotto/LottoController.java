package lotto;

public class LottoController {

    private final InputView inputView;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        LottoStore lottoStore = new LottoStore(inputView);
        LottoMachine lottoMachine = new LottoMachine(inputView, lottoStore.getTicket());
        lottoMachine.getLotteries().findWinners();

        for (Prize prize : Prize.values()) {
            prize.printResult();
        }

        System.out.printf("총 수익률은 %s%%입니다.", Prize.getRateOfReturn(lottoStore.getMoney()));

    }
}
