package lotto;

public class LottoStore {

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
                ticket = money / 1000;
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
        if (money % 1000 != 0 || money < 1000) {
            throw new IllegalArgumentException("[ERROR] 로또는 1개당 1000원입니다.");
        }

    }

    private int parseMoney(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 1000원 단위의 숫자를 입력해주세요.");
        }
    }

}
