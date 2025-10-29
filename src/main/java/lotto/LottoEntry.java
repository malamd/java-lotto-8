package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// 로또 발행기 역할을 하는 클래스 입니다.
public class LottoEntry {
    private final static int LOTTO_PRICE = 1000;
    private final static int PURCHASE_LIMIT = 1000;
    private final static String GET_INPUT_PURCHASE_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String GET_INPUT_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
    private final static String GET_INPUT_BONUS_MESSAGE = "보너스 번호를 입력해 주세요.";
    private final static String PRINT_HOW_MANY_PICKS = "개를 구매했습니다.";
    private static final String INPUT_REGEX = "^[0-9]{1,2}(,[0-9]{1,2}){5}$";
    private static final String DELIMITER = ",";

    private int entries;
    private Picks picks;
    private WinningNumbers winningNumbers;

    // constructor
    LottoEntry() {
        boolean failed = true;
        int purchase = handledPurchase();
        entries = purchase / LOTTO_PRICE;  // 확인 후, 몇번 살지 결정
        picks = new Picks(entries); // 주어진 만큼 발행
        printHowManyPicks(picks);
        setHandledWinningNumbers();


    }

    private void setHandledWinningNumbers() {
        boolean failed = true;
        while (failed) {
            try {
                winningNumbers = new WinningNumbers(getNumbersFromInput()
                        , getWinningNumbersFromInput());
                failed = false;
            } catch (final IllegalArgumentException e) {
                e.getLocalizedMessage();
            }
        }
    }

    private int handledPurchase() {
        boolean failed = true;
        int purchase = -1;
        while (failed) {
            try {
                purchase = getPurchase();
                validatePurchase(purchase);
                failed = false;
            } catch (final IllegalArgumentException e) {
                e.getLocalizedMessage();
            }
        }
        return purchase;
    }

    LottoEntry(int purchase, List<Integer> numbers, int bonus) {
        validatePurchase(purchase);
        entries = purchase / LOTTO_PRICE;
        picks = new Picks(entries); // 주어진 만큼 발행
        winningNumbers = new WinningNumbers(numbers, bonus); // 추첨 번호 받음
    }

    private void printHowManyPicks(Picks picks) {
        System.out.println(entries + PRINT_HOW_MANY_PICKS);
        System.out.println(picks);
    }

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
        in = in.replaceAll("\\s", ""); //공백 삭제
        if (!Pattern.matches(INPUT_REGEX, in)) {
            throw new IllegalArgumentException(SystemMessages.INVALID_INPUT_FORMAT.getTypeAndMessage());
        }
        return parser(in);
    }

    private List<Integer> parser(String in) {
        String[] tokens = in.split(DELIMITER);
        return Arrays.stream(tokens)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
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

    private void validatePurchase(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] TODO"); //TODO
        }
        if (money < 0 || money > PURCHASE_LIMIT) { // bound checking
            throw new IllegalArgumentException("[ERROR] TODO");
        }
    }

    public String getStatisticsReport() {
        return LottoEntryStatistics.statisticsReport(picks, winningNumbers);
    }

}
