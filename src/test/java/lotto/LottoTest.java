package lotto;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1~45의 숫자인지 테스트")
    @Test
    void lottoRangeTest() {

        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5,60)))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void lottoAscendingTest() {
        List<Integer> unsorted = new ArrayList<>(List.of(6, 3, 1, 4, 5, 2));
        Lotto lotto = new Lotto(unsorted);
        List<Integer> copy = new ArrayList<>(lotto.ascendingLotto());
        Collections.sort(unsorted);
        assertThat(copy).isEqualTo(unsorted);
    }

    @Test
    void getNumbers_수정시_예외발생() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> numbers = lotto.getNumbers();
        assertThatThrownBy(() -> numbers.add(7))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void winningCount_당첨_개수_확인(){
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumber winning = new WinningNumber(new Lotto(List.of(1, 2, 3, 7, 8, 9)), 10);

        assertThat(lotto.winningCount(winning)).isEqualTo(3);
    }


    // TODO: 추가 기능 구현에 따른 테스트 코드 작성
}
