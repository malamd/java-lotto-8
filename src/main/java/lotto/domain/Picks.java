package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.dto.UserNumbersInfo;

public class Picks {
    private final List<Lotto> picks;
    private final int purchase;

    public Picks(int purchase){
        this.purchase = purchase;
        picks = new ArrayList<>();
        for (int i = 0; i < purchase; i++) {
            List<Integer> uniqueNumbers = Randoms.pickUniqueNumbersInRange(1,45,6);
            picks.add(new Lotto(uniqueNumbers));
        }
    }

    // 테스트를 위한 생성자 추가
    public Picks(List<Lotto> picks) {
        this.picks = picks;
        this.purchase = picks.size();
    }

    public UserNumbersInfo getNumbersPickAt(int i){
        return picks.get(i).get();
    }

    public List<Lotto> getPicks() {
        return Collections.unmodifiableList(picks);
    }


    @Override
    public String toString() {
        return picks.stream()
                .map(Lotto::toString)
                .collect(Collectors.joining("\n"));
    }

    public int size() {
        return purchase;
    }
}
