package lotto;

public class LottoController {

    private static final String PRINT_TOTAL_PROFIT = "총 수익률은 %s%%입니다.";

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

        System.out.printf(PRINT_TOTAL_PROFIT, Prize.getRateOfReturn(lottoStore.getMoney()));

    }
}
