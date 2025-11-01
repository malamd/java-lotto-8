package lotto;

import lotto.domain.Picks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PicksTest {

    @DisplayName("구입한 로또 개수만큼 Lotto 객체를 생성")
    @Test
    void testPicksConstructor() {
        int purchaseCount = 5;
        Picks picks = new Picks(purchaseCount);
        assertEquals(purchaseCount, picks.size());
    }

    @DisplayName("size 메소드는 구입한 로또 개수를 정확히 반환한다")
    @Test
    void testSize() {
        int purchaseCount = 3;
        Picks picks = new Picks(purchaseCount);
        assertEquals(purchaseCount, picks.size());
    }
}
