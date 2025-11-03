package lotto;

public class WinningNumber {

    private static final String PRINT_DUPLICATED_BONUS_NUMBER_ERROR = "[ERROR] 당첨 번호와 중복 됩니다.";
    private static final String PRINT_RANGE_BONUS_NUMBER_ERROR = "[ERROR] 보너스 번호는 1~45 입니다.";
    private static final int BONUS_MAX_NUMBER = 45;
    private static final int BONUS_MINIMUM_NUMBER = 1;

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
            throw new IllegalArgumentException(PRINT_DUPLICATED_BONUS_NUMBER_ERROR);
        }
    }

    private void validateRangeBonus() {
        if (bonusNumber > BONUS_MAX_NUMBER || bonusNumber < BONUS_MINIMUM_NUMBER) {
            throw new IllegalArgumentException(PRINT_RANGE_BONUS_NUMBER_ERROR);
        }
    }

}
