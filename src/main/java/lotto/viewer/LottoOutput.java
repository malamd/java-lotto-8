package lotto.viewer;

import lotto.domain.Picks;

public class LottoOutput {

    private static final String PRINT_HOW_MANY_PICKS = "개를 구매했습니다.";

    private void printHowManyPicks(Picks picks , int entries) {
        System.out.println(entries + PRINT_HOW_MANY_PICKS);
        System.out.println(picks);
    }
}
