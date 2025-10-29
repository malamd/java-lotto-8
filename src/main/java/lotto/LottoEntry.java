package lotto;

import java.util.ArrayList;

// 로또 발행기 역할을 하는 클래스 입니다.
public class LottoEntry {
    private final static int LOTTO_PRICE = 1000;
    private final static int PURCHASE_LIMIT = 1000;
    private int entries;
    private ArrayList<Lotto> picks;
    private Lotto winningNumber;

    // constructor

    LottoEntry(int purchase) {
        validatePurchase(purchase);
        //확인 후, 몇번 살지 결정
        entries = purchase/LOTTO_PRICE;

        //winningNumber =
    }

    private void validatePurchase(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] TODO"); //TODO
        }
        if (money < 0 || money > PURCHASE_LIMIT) {
            throw new IllegalArgumentException("[ERROR] TODO");
        }
    }
}
