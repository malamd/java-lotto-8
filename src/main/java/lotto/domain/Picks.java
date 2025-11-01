package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Picks {
    private final List<Lotto> picks;

    public Picks(int purchase){
        picks = new ArrayList<>();
        for (int i = 0; i < purchase; i++) {
            Integer [] pick = new Integer[Lotto.getSize()];

            for(int j = 0 ; j < Lotto.getSize() ; i++){
                pick[j] = Randoms.pickNumberInRange(1,45);
            }

            picks.add(new Lotto(Arrays.asList(pick)));
        }


    }
    public Lotto getNumberPickAt(int i){
        return picks.get(i);
    }



    @Override
    public String toString() {
        //TODO: picks에 있는 모든 추첨권들을 주어진 형식에 맞는 문자열로 바꿔야함
        return super.toString();
    }
}
