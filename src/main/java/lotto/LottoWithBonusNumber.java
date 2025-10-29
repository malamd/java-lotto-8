package lotto;

import java.util.List;

// bonus number가 있는 로또 번호입니다

//TODO: getter 없이 해결하는 방법?
public class LottoWithBonusNumber extends Lotto {
    private final int bonusNum;
    private final static int MAX_LOTTO_NUMBER = 45;
    private final static int MIN_LOTTO_NUMBER = 0;

    public LottoWithBonusNumber(List<Integer> numbers, int bonusNumber) {
        super(numbers);
        validateBonusNumber(bonusNumber);
        bonusNum = bonusNumber;
    }


    private void validateBonusNumber(int bonusNumber) {
        if (isDuplicated(bonusNumber)) {
            throw new IllegalArgumentException(SystemMessages.DUPLICATED_LOTTO_NUMBER_FOUND.getTypeAndMessage());
        }

        if(isNotInRange(bonusNumber)){
            throw new IllegalArgumentException(SystemMessages.INVALID_LOTTO_NUMBER_RANGE.getTypeAndMessage());
        }
    }


    private boolean isNotInRange(int number) {
        return getNumbers().contains(number);
    }
    private boolean isDuplicated(int number) {
        return number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER;
    }
}

