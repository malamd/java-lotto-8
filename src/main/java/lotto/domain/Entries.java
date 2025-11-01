package lotto.domain;

import lotto.common.SystemMessages;

public class Entries {
    private static final int LOTTO_PRICE = 1000;
    private static final int PURCHASE_LIMIT = 100000; // 오프라인 구매시 한도
    private long paid;

    public Entries(long purchased){
        validate(purchased);
        paid = purchased;
    }

    public int getEntries(){ // 사실상 getter랑 동일한 역할 아닌지????
        return Math.toIntExact(paid / LOTTO_PRICE);
    }

    private void validate(long money) {
        if (money < 0 || money > PURCHASE_LIMIT) { // bound checking
            throw new IllegalArgumentException(SystemMessages.REACH_THE_LIMIT.getTypeAndMessage());
        }
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(SystemMessages.THERE_IS_CHANGE.getTypeAndMessage());
        }
    }
}
