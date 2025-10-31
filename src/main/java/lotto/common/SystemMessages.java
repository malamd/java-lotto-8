package lotto.common;

public enum SystemMessages {
    INVALID_LOTTO_NUMBER_RANGE("[ERROR]","로또 번호는 1에서 45 사이여야 합니다."),
    DUPLICATED_LOTTO_NUMBER_FOUND("[ERROR]","로또 번호는 중복될 수 없습니다."),
    INVALID_LOTTO_SIZE("[ERROR]","로또 번호는 6개여야 합니다."),
    INVALID_INTEGER_FORMAT("[ERROR]","입력된 숫자가 정수가 아닙니다."),
    INVALID_INPUT_FORMAT("[ERROR]","입력된 추첨번호의 형식이 올바르지 않습니다. "),
    THERE_IS_CHANGE("Error","구입 금액은 1000원 단위로 입력해야합니다."),
    REACH_THE_LIMIT("Error","구입 금액 한도는 10만원입니다.");
    private final String message;
    private final String type;
    SystemMessages(String type, String message) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }
    public String getType(){
        return type;
    }

    public String getTypeAndMessage(){
        return message+type;
    }
}
