package lotto.domain;

import static lotto.common.WinningDetails.FIFTH;
import static lotto.common.WinningDetails.FIRST;
import static lotto.common.WinningDetails.FOURTH;
import static lotto.common.WinningDetails.SECOND;
import static lotto.common.WinningDetails.THIRD;

import java.util.Arrays;
import java.util.List;
import lotto.dto.UserNumbersInfo;

/**
 * 로또의 당첨 결과와 수익률을 계산하는 클래스 입니다.
 */
public class LottoEntryStatistics {
    private static final int RANK = 5;

    /**
     * 당첨 통계와 구매 금액을 바탕으로 총 수익률을 계산합니다. 수익률은 백분율(%) 단위로 계산됩니다.
     *
     * @param winningCounts 각 등수별 당첨 횟수를 담은 배열.
     * @param purchase      총 구매 금액.
     * @return 계산된 총 수익률(%). 예를 들어 62.5%일 경우 62.5를 반환합니다.
     */
    public static float yieldPercentage(Integer[] winningCounts, long purchase) {
        long total = 0;
        total += winningCounts[FIRST.getIndex()] * FIRST.getPrize();
        total += winningCounts[SECOND.getIndex()] * SECOND.getPrize();
        total += winningCounts[THIRD.getIndex()] * THIRD.getPrize();
        total += winningCounts[FOURTH.getIndex()] * FOURTH.getPrize();
        total += winningCounts[FIFTH.getIndex()] * FIFTH.getPrize();

        return (float) total / purchase * 100;
    }

    /**
     * 구매한 로또들과 당첨 번호를 비교하여 각 등수별 당첨 횟수를 계산합니다.
     *
     * @param picks 사용자가 구매한 전체 로또 묶음.
     * @param info  당첨 번호와 보너스 번호 정보를 담은 객체.
     * @return 각 등수별 당첨 횟수를 담은 정수 배열. 배열의 인덱스는 각 등수를 의미합니다.
     */
    public static Integer[] winningCounts(Picks picks, UserNumbersInfo info) {
        Integer[] winningCounts = new Integer[RANK];
        Arrays.fill(winningCounts, 0);

        for (int i = 0; i < picks.size(); i++) {
            int prizeIndex = calculateWinningCounts(info, picks.getNumbersPickAt(i));
            if (prizeIndex != -1) {
                winningCounts[prizeIndex] += 1;
            }
        }

        return winningCounts;
    }

    // 몇등상인지 판단하고 그 상에 맞는 index를 반환함
    private static int calculateWinningCounts(UserNumbersInfo info, UserNumbersInfo currentLotto) {
        List<Integer> winningNumbers = info.getLottoNumbers();
        List<Integer> userNumbers = currentLotto.getLottoNumbers();
        int bonusNumber = info.getBonusNumber();

        long matchCount = userNumbers.stream()
                .filter(winningNumbers::contains)
                .count();

        boolean hasBonus = userNumbers.contains(bonusNumber);

        if (matchCount == FIRST.getBalls()) {
            return FIRST.getIndex();
        }
        if (matchCount == SECOND.getBalls() && hasBonus) {
            return SECOND.getIndex();
        }
        if (matchCount == THIRD.getBalls() && !hasBonus) {
            return THIRD.getIndex();
        }
        if (matchCount == FOURTH.getBalls()) {
            return FOURTH.getIndex();
        }
        if (matchCount == FIFTH.getBalls()) {
            return FIFTH.getIndex();
        }

        return -1; // something is wrong
    }
}
