package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Pattern;

// 로또 발행기 역할을 하는 클래스 입니다.
//TODO: 여기서 던져진 예외 처리해야함....
public class LottoEntry {
    private final static int LOTTO_PRICE = 1000;
    private final static int PURCHASE_LIMIT = 1000;
    private final int entries;
    private final Picks picks;
    private final WinningNumbers winningNumbers;
    private final LottoController statistics;
    private final static String GET_INPUT_MESSAGE = "구입금액을 입력해 주세요.";

    // constructor
    LottoEntry() {
        int purchase = getPurchase();
        validatePurchase(purchase);
        //확인 후, 몇번 살지 결정
        entries = purchase/LOTTO_PRICE;
        picks = new Picks(entries); // 주어진 만큼 발행
        winningNumbers = new WinningNumbers(); // 추첨 번호 받음
        statistics = new LottoController(picks,winningNumbers); // 통계
    }

    private int getPurchase(){
        System.out.println(GET_INPUT_MESSAGE);
        String in = Console.readLine();
        int purchase = -1;
        try{
             purchase = Integer.parseInt(in);
        }catch(final NumberFormatException e){
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
}
