package lotto;

import static lotto.common.WinningDetails.FIFTH;
import static lotto.common.WinningDetails.FIRST;
import static lotto.common.WinningDetails.FOURTH;
import static lotto.common.WinningDetails.SECOND;
import static lotto.common.WinningDetails.THIRD;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoEntryStatistics;
import lotto.domain.Picks;
import lotto.dto.UserNumbersInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StatisticsTest {

    @DisplayName("수익률 계산 테스트 - 단일 당첨")
    @Test
    void testYieldPercentage() {
        Integer[] winningCounts = new Integer[Lotto.getSize()];
        Arrays.fill(winningCounts, 0); // 배열의 모든 요소를 0으로 초기화
        winningCounts[FIFTH.getIndex()] = 1;
        long purchaseAmount = 8000;
        float expectedYield = (float) FIFTH.getPrize() / purchaseAmount;
        float actualYield = LottoEntryStatistics.yieldPercentage(winningCounts, purchaseAmount);
        assertThat(actualYield).isEqualTo(expectedYield);
    }

    @DisplayName("수익률 계산 테스트 - 복수 당첨")
    @Test
    void testYieldPercentageWithMultipleWins() {
        Integer[] winningCounts = new Integer[Lotto.getSize()];
        Arrays.fill(winningCounts, 0);
        winningCounts[FOURTH.getIndex()] = 1; // 4등 1개
        winningCounts[FIFTH.getIndex()] = 2;  // 5등 2개
        long purchaseAmount = 10000; // 10장 구매
        float expectedYield = (float) (FOURTH.getPrize() * 1 + FIFTH.getPrize() * 2) / purchaseAmount;
        float actualYield = LottoEntryStatistics.yieldPercentage(winningCounts, purchaseAmount);
        assertThat(actualYield).isEqualTo(expectedYield);
    }

    @DisplayName("수익률 계산 테스트 - 당첨 없음")
    @Test
    void testYieldPercentageWithNoWins() {
        Integer[] winningCounts = new Integer[Lotto.getSize()];
        Arrays.fill(winningCounts, 0);
        long purchaseAmount = 8000;
        float expectedYield = 0.0f;
        float actualYield = LottoEntryStatistics.yieldPercentage(winningCounts, purchaseAmount);
        assertThat(actualYield).isEqualTo(expectedYield);
    }

    @DisplayName("당첨 통계 계산 테스트 - 3, 4, 5등")
    @Test
    void testWinningCounts() {
        UserNumbersInfo userNumbersInfo = new UserNumbersInfo(List.of(1, 2, 3, 4, 5, 6), 7);

        Picks picks = new Picks(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 8)), // 3등
                new Lotto(List.of(1, 2, 3, 4, 9, 10)), // 4등
                new Lotto(List.of(1, 2, 3, 11, 12, 13))  // 5등
        ));

        Integer[] winningCounts = LottoEntryStatistics.winningCounts(picks, userNumbersInfo);

        assertThat(winningCounts[THIRD.getIndex()]).isEqualTo(1);
        assertThat(winningCounts[FOURTH.getIndex()]).isEqualTo(1);
        assertThat(winningCounts[FIFTH.getIndex()]).isEqualTo(1);
    }

    @DisplayName("당첨 통계 계산 테스트 - 1등, 2등, 꽝")
    @Test
    void testWinningCountsWithFirstAndSecondPlace() {
        UserNumbersInfo userNumbersInfo = new UserNumbersInfo(List.of(1, 2, 3, 4, 5, 6), 7);

        Picks picks = new Picks(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),   // 1등
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),   // 2등
                new Lotto(List.of(10, 11, 12, 13, 14, 15)) // 꽝
        ));

        Integer[] winningCounts = LottoEntryStatistics.winningCounts(picks, userNumbersInfo);

        assertThat(winningCounts[FIRST.getIndex()]).isEqualTo(1);
        assertThat(winningCounts[SECOND.getIndex()]).isEqualTo(1);
        assertThat(winningCounts[THIRD.getIndex()]).isEqualTo(0);
        assertThat(winningCounts[FOURTH.getIndex()]).isEqualTo(0);
        assertThat(winningCounts[FIFTH.getIndex()]).isEqualTo(0);
    }

    @DisplayName("당첨 통계 계산 테스트 - 2등과 3등 구분")
    @Test
    void testDistinctionBetweenSecondAndThirdPlace() {
        UserNumbersInfo userNumbersInfo = new UserNumbersInfo(List.of(1, 2, 3, 4, 5, 6), 7);

        Picks picks = new Picks(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 7)), // 2등
                new Lotto(List.of(1, 2, 3, 4, 5, 8))  // 3등
        ));

        Integer[] winningCounts = LottoEntryStatistics.winningCounts(picks, userNumbersInfo);

        assertThat(winningCounts[SECOND.getIndex()]).isEqualTo(1);
        assertThat(winningCounts[THIRD.getIndex()]).isEqualTo(1);
        assertThat(winningCounts[FIRST.getIndex()]).isZero();
        assertThat(winningCounts[FOURTH.getIndex()]).isZero();
        assertThat(winningCounts[FIFTH.getIndex()]).isZero();
    }
}
