package lotto.domain;

import java.util.Collections;
import lotto.common.SystemMessages;
import java.util.List;
import lotto.dto.UserNumbersInfo;

// 로또 번호
public class Lotto {
    protected static final int MAX_LOTTO_NUMBER = 45;
    protected static final int MIN_LOTTO_NUMBER = 1;
    private static final int SIZE=6;
    private final List<Integer> numbers;


    public Lotto(List<Integer> numbers) {
        Collections.sort(numbers); // sort in ascending order first, then validate numbers.
        validate(numbers);
        this.numbers = numbers;
    }

    public static int getSize(){
        return SIZE;
    }


    // 중복되지 않는 1에서 45까지의 6개의 숫자인지 확인
    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) { // 숫자가 6개인지 확인
            throw new IllegalArgumentException(SystemMessages.INVALID_LOTTO_SIZE
                    .getTypeAndMessage());
        }

        if (isDuplicated(numbers)) { // 중복 확인
            throw new IllegalArgumentException(SystemMessages.DUPLICATED_LOTTO_NUMBER_FOUND
                    .getTypeAndMessage());
        }

        if (isNotInRange(numbers)) { // 범위 확인
            throw new IllegalArgumentException(SystemMessages.INVALID_LOTTO_NUMBER_RANGE
                    .getTypeAndMessage());
        }
    }

    protected boolean isDuplicated(List<Integer> numbers) {
        long countDistinct = numbers.stream().distinct().count();
        return countDistinct != numbers.size();
    }

    protected boolean isNotInRange(List<Integer> numbers) {
        return numbers.stream().anyMatch(num -> num < MIN_LOTTO_NUMBER
                || num > MAX_LOTTO_NUMBER);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public UserNumbersInfo getLottoNumber(){
        return new UserNumbersInfo(numbers);
    }

    // for children classes
    protected List<Integer> getNumbers() {
        return numbers;
    }

}
