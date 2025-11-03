package lotto;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String PRINT_MONEY_INPUT = "구입금액을 입력해 주세요.";
    private static final String PRINT_WINNINGNUMBERS_INPUT = "당첨 번호를 입력해 주세요.";
    private static final String PRINT_BONUS_NUMBER_INPUT = "보너스 번호를 입력해 주세요.";

    public String moneyInput() {
        System.out.println(PRINT_MONEY_INPUT);
        return Console.readLine();
    }

    public String lottoInput() {
        System.out.println(PRINT_WINNINGNUMBERS_INPUT);
        return Console.readLine();
    }

    public String bonusInput() {
        System.out.println(PRINT_BONUS_NUMBER_INPUT);
        return Console.readLine();
    }

}
