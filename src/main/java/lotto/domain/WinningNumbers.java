package lotto.domain;

import lotto.common.SystemMessages;
import java.util.List;
import lotto.dto.UserNumbersInfo;

// bonus number가 있는 로또 번호입니다. 당첨 번호를 지정할때 사용됩니다.
public class WinningNumbers extends Lotto {
    private final int bonusNum;

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

    public UserNumbersInfo get(){
        return new UserNumbersInfo(super.numbers, bonusNum);
    }
}

