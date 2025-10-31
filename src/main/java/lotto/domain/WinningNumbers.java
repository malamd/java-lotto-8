package lotto.domain;

import lotto.common.SystemMessages;
import java.util.List;

// bonus number가 있는 로또 번호입니다. 당첨 번호를 지정할때 사용됩니다.
public class WinningNumbers extends Lotto {
    private final int bonusNum;
    private final static int MAX_LOTTO_NUMBER = 45;
    private final static int MIN_LOTTO_NUMBER = 0;

    public WinningNumbers(List<Integer> numbers, int bonusNumber) {
        super(numbers);
        validateBonusNumber(bonusNumber);
        bonusNum = bonusNumber;
    }


    private void validateBonusNumber(int bonusNumber) {
        if (isDuplicated(bonusNumber)) {
            throw new IllegalArgumentException(SystemMessages.DUPLICATED_LOTTO_NUMBER_FOUND
                    .getTypeAndMessage());
        }

        if(isNotInRange(bonusNumber)){
            throw new IllegalArgumentException(SystemMessages.INVALID_LOTTO_NUMBER_RANGE
                    .getTypeAndMessage());
        }
    }

    private boolean isNotInRange(int number) {
        return getNumbers().contains(number);
    }
    private boolean isDuplicated(int number) {
        return number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER;
    }
}

