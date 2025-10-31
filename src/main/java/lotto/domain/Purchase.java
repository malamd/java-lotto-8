package lotto.domain;

public class Purchase {
    private static final int LOTTO_PRICE = 1000;
    private static final int PURCHASE_LIMIT = 100000; // 오프라인 구매시 한도
    private int paid;

    public Purchase(int purchased){
        validate(purchased);
        paid = purchased;
    }

    public int getEntries(){ // 사실상 getter랑 동일한 메소드 아닌지????
        return paid / LOTTO_PRICE;
    }

    private void validate(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] TODO"); // TODO:
        }
        if (money < 0 || money > PURCHASE_LIMIT) { // bound checking
            throw new IllegalArgumentException("[ERROR] TODO");
        }
    }
}
