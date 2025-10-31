package lotto.domain;

import lotto.common.SystemMessages;
import java.util.List;

// 로또 번호
public class Lotto {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 0;
    private final List<Integer> numbers;


    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
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

    protected List<Integer> getNumbers() {
        return numbers;
    }

}
