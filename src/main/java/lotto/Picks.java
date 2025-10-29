package lotto;

import java.util.ArrayList;
import java.util.List;

public class Picks {
    private final List<Lotto> picks;

    public Picks(int purchase){
        picks = new ArrayList<>();
        for (int i = 0; i < purchase; i++) {
            //picks.append(new Pick());
        }
    }


    @Override
    public String toString() {
        //TODO: picks에 있는 모든 추첨권들을 주어진 형식에 맞는 문자열로 바꿔야함
        return super.toString();
    }
}
