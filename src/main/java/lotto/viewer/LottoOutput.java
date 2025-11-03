package lotto.viewer;

import java.text.NumberFormat;
import lotto.common.WinningDetails;
import lotto.dto.EntriesInfo;
import lotto.dto.StatisticsInfo;

/**
 * 로또 게임의 구매 내역, 당첨 통계 등 모든 출력을 담당하는 클래스입니다.
 */
public class LottoOutput {

    private static final String PRINT_HOW_MANY_ENTRIES = "%d개를 구매했습니다.";
    private static final String PRINT_YIELD_PERCENTAGE = "총 수익률은 %.1f%%입니다.";
    private static final String PRINT_RESULT_TITLE = "당첨 통계\n---";

    /**
     * 구매한 로또의 개수와 각 로또의 번호를 출력합니다. 예시:
     * <pre>
     * 8개를 구매했습니다.
     * [8, 21, 23, 41, 42, 43]
     * [3, 5, 11, 16, 32, 38]
     * ...
     * </pre>
     *
     * @param info 구매한 로또의 개수와 로또 번호 묶음 정보를 담은 DTO.
     */
    public void printHowManyPicks(EntriesInfo info) {
        System.out.println(String.format(PRINT_HOW_MANY_ENTRIES,
                info.getEntries()));
        System.out.println(info.getPicks().toString());
    }

    /**
     * 최종 당첨 통계와 총 수익률을 출력합니다. 각 등수별 당첨 개수와 총 수익률을 정해진 형식에 맞춰 출력합니다. 예시:
     * <pre>
     * 당첨 통계
     * ---
     * 3개 일치 (5,000원) - 1개
     * 4개 일치 (50,000원) - 0개
     * ...
     * 총 수익률은 62.5%입니다.
     * </pre>
     *
     * @param info 당첨 통계 및 수익률 정보를 담은 DTO.
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
    private String getRankString(int i, int counts) {

        // Code Assistant가 제안한 수정방안 - 기존: 여러개의 if 문
        for (WinningDetails detail : WinningDetails.values()) {
            if (detail.getIndex() == i) {
                String prize = NumberFormat.getInstance().format(detail.getPrize());
                return String.format(detail.getMessage(), detail.getBalls(), prize, counts);
            }
        }

        return "SOMETHING is WRONG"; // Something is wrong
    }

}
