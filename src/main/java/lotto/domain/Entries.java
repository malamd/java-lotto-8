package lotto.domain;

public class Entries {
    private static final int LOTTO_PRICE = 1000;
    private static final int PURCHASE_LIMIT = 1000;
    private int paid;

    public Entries(int purchased){
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
