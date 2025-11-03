package lotto;

import java.util.Arrays;
import java.util.List;

public class Lotteries {

    private final List<Lotto> lottos;
    private final WinningNumber winningNumbers;

    public Lotteries(List<Lotto> lottos, WinningNumber winningNumbers) {
        this.lottos = lottos;
        this.winningNumbers = winningNumbers;
    }

    public void findWinners() {

        for (Lotto lotto : lottos) {
            Prize.check(lotto.winningCount(winningNumbers), lotto.haveBonusNumber(winningNumbers));
        }
    }

}
