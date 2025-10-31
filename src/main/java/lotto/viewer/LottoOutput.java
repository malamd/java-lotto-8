package lotto.viewer;

import lotto.dto.EntriesInfo;
import lotto.dto.StatisticsInfo;

public class LottoOutput {

    private static final String PRINT_HOW_MANY_ENTRIES = "개를 구매했습니다.";

    private void printHowManyPicks(EntriesInfo info) {
        System.out.println(info.getEntries() + PRINT_HOW_MANY_ENTRIES);
        System.out.println(info.getPicks());
    }
    private void printStatistic(StatisticsInfo info) {
        //TODO: format printing
    }
}
