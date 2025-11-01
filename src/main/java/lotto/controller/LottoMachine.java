package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lotto.common.SystemMessages;
import lotto.domain.LottoEntryStatistics;
import lotto.domain.Picks;
import lotto.domain.Purchase;
import lotto.domain.WinningNumbers;
import lotto.dto.EntriesInfo;
import lotto.dto.StatisticsInfo;


/**
 * 로또 발행기 클래스입니다.
 * 주어진 입력으로부터 로또를 얼만큼 발행해야할지 결정하고,당첨 번호와 보너스 번호도 결정합니다.
 * 저장한 발행 로또, 당첨 번호,보너스 번호를 LottoEntryStatistics에 전달하고
 * 받은 당첨내역과 통계를 Output에 전달합니다.
 */
public class LottoMachine {
    private static final String GET_INPUT_PURCHASE_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String GET_INPUT_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String GET_INPUT_BONUS_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final String INPUT_REGEX = "^[0-9]{1,2}(,[0-9]{1,2}){5}$";
    private static final String DELIMITER = ",";

    private int entries;
    private Picks picks;
    private long purchase;

    // constructor
    public LottoMachine() {
        purchase = getPurchase();
        entries = getEntries(purchase);
        picks = new Picks(entries); // 주어진 만큼 발행
    }

    LottoMachine(int purchase, List<Integer> numbers, int bonus) {
        entries =  getEntries(purchase);
        picks = new Picks(entries); // 주어진 만큼 발행
    }

    // deliver to LottoOutput
    public EntriesInfo getEntriesInfo(){
        return new EntriesInfo(entries,picks);
    }
    public StatisticsInfo getStatisticsInfo(){
        WinningNumbers winningNumbers = getHandledWinningNumbers();

        Integer[] report =  LottoEntryStatistics.winningCounts(picks,winningNumbers.get());
        float winningRate = LottoEntryStatistics.yieldPercentage(report,purchase);

        return new StatisticsInfo(report,winningRate);
    }

    // helper functions
    private WinningNumbers getHandledWinningNumbers() {
        WinningNumbers winningNumbers;
        while (true) {
            try {
                winningNumbers = new WinningNumbers(getNumbersFromInput(),
                        getWinningNumbersFromInput());
                return winningNumbers;

            } catch (final IllegalArgumentException e) {
                e.getLocalizedMessage();
            }
        }
    }


    // be used in getHandledWinningNumbers only
    private int getWinningNumbersFromInput() {
        System.out.println(GET_INPUT_NUMBERS_MESSAGE);
        String in = Console.readLine();
        int bonusNumber = -1;
        try {
            bonusNumber = Integer.parseInt(in);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(SystemMessages.INVALID_INTEGER_FORMAT
                    .getTypeAndMessage());
        }
        return bonusNumber;
    }


    private List<Integer> getNumbersFromInput() {
        System.out.println(GET_INPUT_BONUS_MESSAGE);
        String in = Console.readLine();
        in = in.replaceAll("\\s", ""); // 공백 삭제
        if (!Pattern.matches(INPUT_REGEX, in)) {
            throw new IllegalArgumentException(SystemMessages.INVALID_INPUT_FORMAT
                    .getTypeAndMessage());
        }
        return parser(in);
    }

    // used in getNumbersFromInput only
    private List<Integer> parser(String in) {
        String[] tokens = in.split(DELIMITER);
        return Arrays.stream(tokens)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private int getEntries(long purchase) {
        while (true) {
            try {
                entries =  new Purchase(purchase).getEntries();// 확인 후, 몇번 살지 결정
                return entries;
            } catch (final IllegalArgumentException e) {
                e.getLocalizedMessage();
            }
        }
    }


    private int getPurchase() {
        System.out.println(GET_INPUT_PURCHASE_MESSAGE);
        String in = Console.readLine();
        int purchase = -1;
        try {
            purchase = Integer.parseInt(in);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(SystemMessages.INVALID_INTEGER_FORMAT
                    .getTypeAndMessage());
        }
        return purchase;
    }

}
