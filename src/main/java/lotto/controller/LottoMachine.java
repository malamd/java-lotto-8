package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lotto.common.SystemMessages;
import lotto.domain.Entries;
import lotto.domain.LottoEntryStatistics;
import lotto.domain.Picks;
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

    private final int entries;
    private final Picks picks;
    private final long purchase;

    // constructor
    public LottoMachine() {
        this.purchase = getValidPurchaseAmount();
        this.entries = new Entries(this.purchase).getEntries();
        this.picks = new Picks(this.entries);
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
        while (true) {
            try {
                List<Integer> numbers = getNumbersFromInput();
                int bonusNumber = getBonusNumberFromInput();
                return new WinningNumbers(numbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getBonusNumberFromInput() {
        System.out.println(GET_INPUT_BONUS_MESSAGE);
        String in = Console.readLine();
        try {
            return Integer.parseInt(in);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(SystemMessages.INVALID_INTEGER_FORMAT.getTypeAndMessage());
        }
    }

    private List<Integer> getNumbersFromInput() {
        System.out.println(GET_INPUT_NUMBERS_MESSAGE);
        String in = Console.readLine().replaceAll("\\s", "");
        if (!Pattern.matches(INPUT_REGEX, in)) {
            throw new IllegalArgumentException(SystemMessages.INVALID_INPUT_FORMAT.getTypeAndMessage());
        }
        return parser(in);
    }

    private List<Integer> parser(String in) {
        return Arrays.stream(in.split(DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private long getValidPurchaseAmount() {
        System.out.println(GET_INPUT_PURCHASE_MESSAGE);
        while (true) {
            try {
                long purchaseAmount = purchaseParser(Console.readLine());
                new Entries(purchaseAmount); // 유효성 검사를 위해 Entries 객체 생성
                return purchaseAmount;
            } catch (final IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private long purchaseParser(String in){
        try{
            return Long.parseLong(in);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException(SystemMessages.INVALID_INTEGER_FORMAT.getTypeAndMessage());
        }
    }
}
