package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoTest {
    @DisplayName("로또 번호 개수가 6개 보다 많을때 예외 발생.")
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

    @DisplayName("로또 번호의 개수가 6개 보다 적을때 예외 발생.")
    @Test
    void TestLottoNumbersLessThanSix() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("로또 번호가 45를 넘을때 예외 발생.")
    @Test
    void TestLottoNumberOutOfTheRange1() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 50)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 0보다 작을때 예외 발생.")
    @Test
    void TestLottoNumberOutOfTheRange2() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, -4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호를 올바른 형식으로 문자열로 변환하는지 확인.")
    @Test
    void TestLottoToString() {
        String expected = "[8, 21, 23, 41, 42, 43]";
        Lotto test= new Lotto(List.of(8,21,23,41,42,43));
        String actual = test.toString();
        assertEquals(expected,actual,"Expected : " + expected + " but found " + actual);
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성
}
