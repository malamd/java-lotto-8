package lotto.domain;

import lotto.common.SystemMessages;
import java.util.List;
import lotto.dto.UserNumbersInfo;

/**
 * 당첨 번호와 보너스 번호를 함께 관리하는 클래스입니다.
 */
public class WinningNumbers extends Lotto {
    private final int bonusNum;

    /**
     * 당첨 번호 리스트와 보너스 번호로 WinningNumbers 객체를 생성합니다.
     * 생성 시 당첨 번호의 유효성(Lotto의 규칙)과 보너스 번호의 유효성(범위, 당첨 번호와의 중복)을 모두 검사합니다.
     *
     * @param numbers 당첨 번호를 담은 숫자 리스트.
     * @param bonusNumber 보너스 번호.
     * @throws IllegalArgumentException 당첨 번호 또는 보너스 번호가 유효하지 않을 경우 발생합니다.
     */
    public WinningNumbers(List<Integer> numbers, int bonusNumber) {
        super(numbers);
        validateBonusNumber(bonusNumber);
        bonusNum = bonusNumber;
    }


    private void validateBonusNumber(int bonusNumber) {
        if (isDuplicated(bonusNumber)) { // 로또 번호와 중복되는 보너스 번호일 경우
            throw new IllegalArgumentException(SystemMessages.DUPLICATED_LOTTO_NUMBER_FOUND
                    .getTypeAndMessage());
        }

        if(isNotInRange(bonusNumber)){ // 범위를 벗어난 보너스 번호일 경우
            throw new IllegalArgumentException(SystemMessages.INVALID_LOTTO_NUMBER_RANGE
                    .getTypeAndMessage());
        }
    }
    private boolean isNotInRange(int number) {
        return number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER;
    }
    private boolean isDuplicated(int number) {
        return super.numbers.contains(number);
    }

    /**
     * 당첨 번호와 보너스 번호 정보를 담은 데이터 전송 객체(DTO)를 반환합니다.
     * 반환되는 객체는 원본 데이터를 수정할 수 없습니다.
     *
     * @return 당첨 번호와 보너스 번호 정보를 담은 UserNumbersInfo 객체.
     */
    public UserNumbersInfo get(){
        return new UserNumbersInfo(super.numbers, bonusNum);
    }
}

