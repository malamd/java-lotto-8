package lotto.viewer;

import lotto.dto.EntriesInfo;
import lotto.dto.StatisticsInfo;

public class LottoOutput {

    private static final String PRINT_HOW_MANY_ENTRIES = "개를 구매했습니다.";

    public void printHowManyPicks(EntriesInfo info) {
        System.out.println(info.getEntries() + PRINT_HOW_MANY_ENTRIES);
        System.out.println(info.getPicks());
    }
    public void printStatistic(StatisticsInfo info) {
        Integer[] winningCounts = info.getWinningCounts();
        float yieldPercentage = info.getYieldPercentage();

        //TODO: format output
        for(Integer i : winningCounts){
            System.out.println(i);
        }

        System.out.println(yieldPercentage);


    }
}
