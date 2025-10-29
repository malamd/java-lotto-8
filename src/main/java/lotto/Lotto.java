package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// 로또 번호
public class Lotto {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 0;
    private final List<Integer> numbers;
    private static final String GET_INPUT_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final String INPUT_REGEX = "^[0-9]{1,2}(,[0-9]{1,2}){5}$";
    private static final String DELIMITER = ",";

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    // 확장성이 좋다고는 말하기 어려운듯..
    public Lotto() {
        List<Integer> numbers = getNumbersFromInput();
        validate(numbers);
        this.numbers = numbers;
    }

    private List<Integer> getNumbersFromInput() {
        System.out.println(GET_INPUT_MESSAGE);
        String in = Console.readLine();
        in = in.replaceAll("\\s", ""); //공백 삭제
        if (!Pattern.matches(INPUT_REGEX, in)) {
            throw new IllegalArgumentException(SystemMessages.INVALID_INPUT_FORMAT.getTypeAndMessage());
        }
        return parser(in);
    }

    private List<Integer> parser(String in) {
        String[] tokens = in.split(DELIMITER);
        return Arrays.stream(tokens)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
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
