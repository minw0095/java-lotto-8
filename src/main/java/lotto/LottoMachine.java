package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_MINIMUM_NUMBER = 1;
    private static final int LOTTO_PICK_NUMBER = 6;
    private static final String PRINT_LOTTO_COUNT = "%s개를 구매했습니다.";
    private static final String PRINT_WINNINGNUMBERS_SIZE_ERROR = "[ERROR] 6자리 숫자를 적어주세요.";
    private static final String PRINT_WINNINGNUMBERS_NOT_NUMBER_ERROR = "[ERROR] 숫자를 적어주세요.";
    private static final String SPLIT_REGEX = ",";


    private final InputView inputView;
    private final int ticket;

    public LottoMachine(InputView inputView, int ticket) {
        this.inputView = inputView;
        this.ticket = ticket;
    }

    private List<Integer> pickRandomNumbers() {
        return Randoms.pickUniqueNumbersInRange(LOTTO_MINIMUM_NUMBER, LOTTO_MAX_NUMBER, LOTTO_PICK_NUMBER);
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
        System.out.printf(PRINT_LOTTO_COUNT, ticket);
        System.out.println();
        lotteries.forEach(e-> System.out.println(e.ascendingLotto()));
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
            return Arrays.stream(number.split(SPLIT_REGEX)).map(Integer::parseInt).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PRINT_WINNINGNUMBERS_SIZE_ERROR);
        }
    }

    private int parseNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PRINT_WINNINGNUMBERS_NOT_NUMBER_ERROR);
        }
    }

    public Lotteries getLotteries() {
        return new Lotteries(makeLotteries(), makeWinningNumber());
    }

}
