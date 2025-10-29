package lotto;

import java.util.ArrayList;

// 로또 발행기 역할을 하는 클래스 입니다.
public class LottoController {
    private final static int LOTTO_PRICE = 1000;
    private final static int PURCHASE_LIMIT = 1000;
    private final int entries;
    private final Picks picks;

    // constructor

    LottoController(int purchase) {
        validatePurchase(purchase);
        //확인 후, 몇번 살지 결정
        entries = purchase/LOTTO_PRICE;

        //TODO: set the winning number
        picks = new Picks(entries); // 주어진 만큼 발행
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
