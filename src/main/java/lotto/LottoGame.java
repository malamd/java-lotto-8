package lotto;

import lotto.controller.LottoMachine;
import lotto.viewer.LottoOutput;

public class LottoGame {
    public static void start() {
        LottoMachine lottoMachine = new LottoMachine();
        LottoOutput lottoOutput = new LottoOutput();
        lottoOutput.printHowManyPicks(lottoMachine.getEntriesInfo());
    }

}
