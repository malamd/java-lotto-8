package lotto;

import java.util.List;

// bonus number가 있는 로또 번호입니다

//TODO: 문제점- 단순한 상속으로는 주어진 가이드라인을 맞출 수 없음
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
        if (bonusNumber < MIN_LOTTO_NUMBER || bonusNumber > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException();//TODO
        }
    }

    // TODO: getter를 쓰지 말라는게 뭔소리야?
    public int getBonusNum() {
        return bonusNum;
    }
}

