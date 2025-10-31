package lotto;

import lotto.domain.Purchase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PurchaseTest {
    private static final int PURCHASE_LIMIT = 100000;

    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다")
    @Test
    void throwExceptionForInvalidPurchaseAmount() {
        assertThatThrownBy(() -> new Purchase(1500))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 음수이면 예외가 발생한다")
    @Test
    void throwExceptionForNegativePurchaseAmount() {
        assertThatThrownBy(() -> new Purchase(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 한도를 초과하면 예외가 발생한다")
    @Test
    void throwExceptionForExceedingPurchaseLimit() {
        assertThatThrownBy(() -> new Purchase(PURCHASE_LIMIT+10000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정상적인 구입 금액일 경우 예외가 발생하지 않는다")
    @Test
    void doesNotThrowExceptionForValidPurchaseAmount() {
        assertDoesNotThrow(() -> new Purchase(1000));
    }

    @DisplayName("구입한 로또 개수를 정확히 반환한다")
    @Test
    void returnsCorrectNumberOfLottos() {
        Purchase entries = new Purchase(12000);
        assertEquals(12, entries.getEntries());
    }
}