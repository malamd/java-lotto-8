package lotto.viewer;

import java.text.NumberFormat;
import lotto.common.WinningDetails;
import lotto.dto.EntriesInfo;
import lotto.dto.StatisticsInfo;

// 로또 게임의 출력을 담당하는 클래스 입니다.
public class LottoOutput {

    private static final String PRINT_HOW_MANY_ENTRIES = "%d개를 구매했습니다.";
    private static final String PRINT_YIELD_PERCENTAGE = "총 수익률은 %.1f%%입니다.";
    private static final String PRINT_RESULT_TITLE = "당첨 통계\n---";

    /**
     *
     * @param info
     */
    public void printHowManyPicks(EntriesInfo info) {
        System.out.println(String.format(PRINT_HOW_MANY_ENTRIES,
                info.getEntries()));
        System.out.println(info.getPicks().toString());
    }

    /**
     *
     * @param info
     */
    public void printStatistic(StatisticsInfo info) {
        Integer[] winningCounts = info.getWinningCounts();
        float yieldPercentage = info.getYieldPercentage();

        System.out.println(PRINT_RESULT_TITLE);
        for (int i = 0; i < winningCounts.length; i++) {
            String rankString = getRankString(i, winningCounts[i]);
            System.out.println(rankString);
        }
        System.out.println(String.format(PRINT_YIELD_PERCENTAGE, yieldPercentage));

    }

    // helper function for printStatistic, only be used in printStatistic
    private String getRankString(int i, int counts) { //TODO: 코드양 줄이는 방법?

        /**
         * Code Assistant가 제안한 수정방안 - 기존: 여러개의 if 문
         */
        for (WinningDetails detail : WinningDetails.values()) {
            if (detail.getIndex() == i) {
                String prize = NumberFormat.getInstance().format(detail.getPrize());
                return String.format(detail.getMessage(), detail.getBalls(), prize, counts);
            }
        }

        return "SOMETHING is WRONG"; // Something is wrong
    }

}
