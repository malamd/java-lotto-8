package lotto.domain;

import static lotto.common.WinningDetails.FIFTH;
import static lotto.common.WinningDetails.FIRST;
import static lotto.common.WinningDetails.FOURTH;
import static lotto.common.WinningDetails.SECOND;
import static lotto.common.WinningDetails.THIRD;

import java.util.Arrays;
import java.util.List;
import lotto.dto.UserNumbersInfo;

public class LottoEntryStatistics {
    private final static String REPORT_MESSAGE = "";
    private final static int RANK =5;
    //TODO: 통계 테스트

    public static float yieldPercentage(Integer[] winningCounts, long purchase){
        long total = 0;
        total+=winningCounts[FIRST.getIndex()]* FIRST.getPrize();
        total+=winningCounts[SECOND.getIndex()]* SECOND.getPrize();
        total+=winningCounts[THIRD.getIndex()]* THIRD.getPrize();
        total+=winningCounts[FOURTH.getIndex()]* FOURTH.getPrize();
        total+=winningCounts[FIFTH.getIndex()]* FIFTH.getPrize();

        return (float)total/purchase*100;
    }

    public static Integer[] winningCounts(Picks picks, UserNumbersInfo info) {
        Integer[] winningCounts = new Integer[RANK];
        Arrays.fill(winningCounts, 0);

        for (int i = 0; i < picks.size(); i++) {
            int prizeIndex = calculateWinningRate(info, picks.getNumbersPickAt(i));
            if (prizeIndex != -1) {
                winningCounts[prizeIndex] += 1;
            }
        }

        return winningCounts;
    }
    // 몇등상인지 판단하고 그 상에 맞는 index를 반환함
    private static int calculateWinningRate(UserNumbersInfo info, UserNumbersInfo currentLotto) {
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
