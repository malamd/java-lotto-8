package lotto.dto;

import java.util.List;

public class UserNumbersInfo {
    private final List<Integer> numbers;
    private final int bonusNumber;

    public UserNumbersInfo(List<Integer> numbers){
        this.numbers = numbers;
        this.bonusNumber = -1;
    }
    public UserNumbersInfo(List<Integer> numbers,int bonusNumber){
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getLottoNumbers(){
        return numbers;
    }
    public int getBonusNumber(){
        return bonusNumber;
    }
}
