package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final String PRINT_LOTTO_SIZE_ERROR = "[ERROR] 로또 번호는 6개여야 합니다.";
    private static final String PRINT_LOTTO_DUPLICATED_NUMBER_ERROR = "[ERROR] 중복되는 숫자는 안됩니다.";
    private static final String PRINT_LOTTO_RANGE_NUMBER_ERROR = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
        validateSameNumber();
        validateRangeNumber();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(PRINT_LOTTO_SIZE_ERROR);
        }
    }

    public void printNumbers() {
        System.out.println(ascendingLotto());
    }

    public List<Integer> ascendingLotto() {
        return numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    private void validateSameNumber() {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException(PRINT_LOTTO_DUPLICATED_NUMBER_ERROR);
        }
    }

    private void validateRangeNumber() {
        if (numbers.stream().anyMatch(e -> e > 45 || e < 1)) {
            throw new IllegalArgumentException(PRINT_LOTTO_RANGE_NUMBER_ERROR);
        }
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public long winningCount(WinningNumber winningNumber) {
        return numbers.stream()
                .filter(winningNumber.getNumber().numbers::contains)
                .count();
    }

    public boolean haveBonusNumber(WinningNumber winningNumber) {
        if (winningCount(winningNumber) == 5) {
            return numbers.contains(winningNumber.getBonusNumber());
        }
        return false;
    }

    // TODO: 추가 기능 구현
}
