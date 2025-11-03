package lotto.domain;

import lotto.common.SystemMessages;

/**
 * 사용자의 로또 구매 금액을 관리하고, 구매 가능한 로또의 개수를 계산하는 클래스입니다.
 */
public class Entries {
    private static final int LOTTO_PRICE = 1000;
    private static final int PURCHASE_LIMIT = 100000; // 오프라인 구매시 한도
    private long paid;

    /**
     * 구매 금액으로 Entries 객체를 생성합니다. 생성 시 구매 금액의 유효성(1,000원 단위, 구매 한도)을 검사합니다.
     *
     * @param purchased 사용자가 지불한 구매 금액.
     * @throws IllegalArgumentException 구매 금액이 1,000원 단위가 아니거나, 0원 미만 또는 구매 한도를 초과할 경우 발생합니다.
     */
    public Entries(long purchased) {
        validate(purchased);
        paid = purchased;
    }

    /**
     * 지불된 금액으로 구매할 수 있는 로또의 총 개수를 반환합니다.
     *
     * @return 구매 가능한 로또의 개수.
     */
    public int getEntries() {
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
