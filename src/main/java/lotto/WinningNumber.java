package lotto;

public class WinningNumber {

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningNumber(Lotto lotto, int bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
        validateSameBonusNumber();
        validateRangeBonus();
    }

    public Lotto getNumber() {
        return lotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validateSameBonusNumber() {
        if (lotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("[Error] 당첨 번호와 중복 됩니다.");
        }
    }

    private void validateRangeBonus() {
        if (bonusNumber > 45 || bonusNumber < 1) {
            throw new IllegalArgumentException("[Error] 보너스 번호는 1~45 입니다.");
        }
    }

}
