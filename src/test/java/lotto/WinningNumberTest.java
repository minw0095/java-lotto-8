package lotto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumberTest {

    @Test
    void sameBonusNumberTest() {
        Lotto lottos = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonus = 6;

        assertThatThrownBy(() -> new WinningNumber(lottos, bonus))
                .isInstanceOf(IllegalArgumentException.class);

    }

}
