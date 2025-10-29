package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

// bonus number가 있는 로또 번호입니다. 당첨 번호를 지정할때 사용됩니다.
public class WinningNumbers extends Lotto {
    private final int bonusNum;
    private final static int MAX_LOTTO_NUMBER = 45;
    private final static int MIN_LOTTO_NUMBER = 0;
    private final static String GET_INPUT_MESSAGE = "보너스 번호를 입력해 주세요.";

    public WinningNumbers(List<Integer> numbers, int bonusNumber) {
        super(numbers);
        validateBonusNumber(bonusNumber);
        bonusNum = bonusNumber;
    }

    public WinningNumbers(){
        super();
        int bonusNumber = getWinningNumbersFromInput();
        validateBonusNumber(bonusNumber);
        bonusNum = bonusNumber;
    }

    private int getWinningNumbersFromInput(){
        System.out.println(GET_INPUT_MESSAGE);
        String in = Console.readLine();
        int bonusNumber = -1;
        try{
            bonusNumber = Integer.parseInt(in);
        }catch(final NumberFormatException e){
            throw new IllegalArgumentException(SystemMessages.INVALID_INTEGER_FORMAT
                    .getTypeAndMessage());
        }
        return bonusNumber;
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

