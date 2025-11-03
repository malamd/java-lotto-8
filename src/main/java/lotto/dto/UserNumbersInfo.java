package lotto.dto;

import java.util.Collections;
import java.util.List;

/**
 * 로또 번호 리스트와 보너스 번호 정보를 담아 전달하는 데이터 전송 객체(DTO)입니다. 이 객체는 수정이 불가능합니다.
 */
public class UserNumbersInfo {
    private final List<Integer> numbers;
    private final int bonusNumber;

    /**
     * 로또 번호 리스트만으로 객체를 생성합니다. 보너스 번호는 -1로 초기화됩니다.
     *
     * @param numbers 로또 번호를 담은 숫자 리스트.
     */
    public UserNumbersInfo(List<Integer> numbers) {
        this.numbers = numbers;
        this.bonusNumber = -1;
    }

    /**
     * 로또 번호 리스트와 보너스 번호로 객체를 생성합니다.
     *
     * @param numbers     로또 번호를 담은 숫자 리스트.
     * @param bonusNumber 보너스 번호.
     */
    public UserNumbersInfo(List<Integer> numbers, int bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    /**
     * 로또 번호의 수정 불가능한 리스트를 반환합니다.
     *
     * @return 수정 불가능한 로또 번호 리스트.
     */
    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(this.numbers);
    }

    /**
     * 보너스 번호를 반환합니다.
     *
     * @return 보너스 번호. 보너스 번호가 없는 경우 -1을 반환할 수 있습니다.
     */
    public int getBonusNumber() {
        return bonusNumber;
    }
}
