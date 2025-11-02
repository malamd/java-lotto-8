package lotto.viewer;

import java.text.NumberFormat;
import lotto.common.WinningDetails;
import lotto.dto.EntriesInfo;
import lotto.dto.StatisticsInfo;

public class LottoOutput {

    private static final String PRINT_HOW_MANY_ENTRIES = "%d개를 구매했습니다.";
    private static final String PRINT_YIELD_PERCENTAGE = "총 수익률은 %.1f%%입니다.";
    private static final String PRINT_RESULT_TITLE = "당첨 통계\n---";

    public void printHowManyPicks(EntriesInfo info) {
        System.out.println(String.format(PRINT_HOW_MANY_ENTRIES,
                info.getEntries()));
        System.out.println(info.getPicks().toString());
    }

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


    private String getRankString(int i, int counts) { //TODO: 코드양 줄이는 방법?인진 모르겠고 확장성 생각하면  strategy 쓰면 되나..

        if (i == WinningDetails.FIRST.getIndex()) {
            String message = WinningDetails.FIRST.getMessage();
            int balls = WinningDetails.FIRST.getBalls();
            String prize = NumberFormat.getInstance().format(WinningDetails.FIRST.getPrize());
            return String.format(message, balls, prize, counts);
        }

        if (i == WinningDetails.SECOND.getIndex()) {
            String message = WinningDetails.SECOND.getMessage();
            int balls = WinningDetails.SECOND.getBalls();
            String prize = NumberFormat.getInstance().format(WinningDetails.SECOND.getPrize());
            return String.format(message, balls, prize, counts);

        }

        if (i == WinningDetails.THIRD.getIndex()) {
            String message = WinningDetails.THIRD.getMessage();
            int balls = WinningDetails.THIRD.getBalls();
            String prize = NumberFormat.getInstance().format(WinningDetails.THIRD.getPrize());
            return String.format(message, balls, prize, counts);

        }

        if (i == WinningDetails.FOURTH.getIndex()) {
            String message = WinningDetails.FOURTH.getMessage();
            int balls = WinningDetails.FOURTH.getBalls();
            String prize = NumberFormat.getInstance().format(WinningDetails.FOURTH.getPrize());
            return String.format(message, balls, prize, counts);
        }

        if (i == WinningDetails.FIFTH.getIndex()) {
            int balls = WinningDetails.FIFTH.getBalls();
            String prize = NumberFormat.getInstance().format(WinningDetails.FIFTH.getPrize());
            return String.format(WinningDetails.FIFTH.getMessage(), balls, prize, counts);
        }

        return "SOMETHING is WRONG"; // Something is wrong
    }

}
