package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.common.SystemMessages;
import lotto.dto.UserNumbersInfo;


/**
 * 로또 번호를 담당하는 클래스입니다.
 */
public class Lotto {
    protected static final int MAX_LOTTO_NUMBER = 45;
    protected static final int MIN_LOTTO_NUMBER = 1;
    private static final int SIZE = 6;
    protected final List<Integer> numbers;

    /**
     * 주어진 숫자 리스트로 로또 객체를 생성합니다.
     * 생성 시 로또 번호의 유효성(개수, 중복, 범위)을 검사합니다.
     *
     * @param numbers 로또 번호를 담은 숫자 리스트
     * @throws IllegalArgumentException 로또 번호가 6개가 아니거나, 중복된 숫자가 있거나, 1-45 범위를 벗어나는 숫자가 있을 경우 발생합니다.
     */
    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }



    /**
     * 로또 번호의 갯수를 반환합니다. 반환되는 값은 무조건 6입니다.
     *
     * @return 6, 로또 번호의 갯수
     */
    public static int getSize() {
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

    // for child class
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
        // 불변이기 때문에 List.copyOf()대신 출력용 새로운 ArrayList 생성
        List<Integer> forOutput = new ArrayList<>(this.numbers);
        Collections.sort(forOutput); // testcase 때문에 출력할때 정렬함

        return forOutput.toString();
    }

    /**
     * 현재 로또 번호를 UserNumberInfo로 반환합니다.
     * 반환된 값은 getLottoNumbers()로 번호를 가져올 수 있습니다.
     *
     * @return 로또 번호 정보를 담고 있는 UserNumbersInfo
     */
    public UserNumbersInfo get() {
        return new UserNumbersInfo(Collections.unmodifiableList(numbers)); // read_only
    }


}
