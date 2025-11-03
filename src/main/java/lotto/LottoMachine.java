package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {

    private final InputView inputView;
    private final int ticket;

    public LottoMachine(InputView inputView, int ticket) {
        this.inputView = inputView;
        this.ticket = ticket;
    }

    private List<Integer> pickRandomNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }

    private List<Lotto> makeLotteries() {
        List<Lotto> lotteries = new ArrayList<>();

        for (int i = 0; i < ticket; i++) {
            lotteries.add(new Lotto(pickRandomNumbers()));
        }

        printLotteries(lotteries);

        return lotteries;
    }

    private void printLotteries(List<Lotto> lotteries) {
        System.out.printf("%s개를 구매했습니다.", ticket);
        System.out.println();
        lotteries.forEach(Lotto::printNumbers);
    }

    private WinningNumber makeWinningNumber() {
        Lotto winningNumber = lottoWinningNumber();
        while (true) {
            try {
                int bonus = parseBonusNumber();
                return new WinningNumber(winningNumber, bonus);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lotto lottoWinningNumber() {
        while (true) {
            try {
                List<Integer> lotto = lottoNumber(inputView.lottoInput());
                return new Lotto(lotto);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private int parseBonusNumber() {
        while (true) {
            try {
                return parseNumber(inputView.bonusInput());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<Integer> lottoNumber(String number) {
        try {
            return Arrays.stream(number.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 6자리 숫자를 적어주세요.");
        }
//
    }

    private int parseNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 적어주세요.");
        }
    }

    public Lotteries getLotteries() {
        return new Lotteries(makeLotteries(), makeWinningNumber());
    }

}
