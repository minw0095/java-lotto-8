package lotto;

public class LottoStore {

    private static final String PRINT_MONEY_THOUSAND_UNIT_ERROR = "[ERROR] 로또는 1개당 1000원입니다.";
    private static final String PRINT_MONEY_NOT_NUMBER_ERROR = "[ERROR] 1000원 단위의 숫자를 입력해주세요.";
    private static final int MONEY_MINIMUM_UNIT = 1000;


    private final InputView inputView;
    private int money;
    private int ticket;

    public LottoStore(InputView inputView) {
        this.inputView = inputView;
        buyTickets();
    }

    public void buyTickets() {
        while (true) {
            try {
                String moneyInput = inputView.moneyInput();
                money = parseMoney(moneyInput);
                validatePerThousand(money);
                ticket = money / MONEY_MINIMUM_UNIT;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int getTicket() {
        return this.ticket;
    }

    public int getMoney() {
        return money;
    }

    public void validatePerThousand(int money) {
        if (money % MONEY_MINIMUM_UNIT != 0 || money < MONEY_MINIMUM_UNIT) {
            throw new IllegalArgumentException(PRINT_MONEY_THOUSAND_UNIT_ERROR);
        }

    }

    private int parseMoney(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PRINT_MONEY_NOT_NUMBER_ERROR);
        }
    }

}
