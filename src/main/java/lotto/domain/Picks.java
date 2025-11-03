package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.dto.UserNumbersInfo;

// 발행한 로또 번호들을 모아둔 클래스 입니다.
public class Picks {
    private final List<Lotto> picks;
    private final int purchase;

    // constructor for actual Application
    public Picks(int purchase){
        this.purchase = purchase;
        picks = new ArrayList<>();
        for (int i = 0; i < purchase; i++) {
            List<Integer> uniqueNumbers = Randoms.pickUniqueNumbersInRange(1,45,6);
            picks.add(new Lotto(uniqueNumbers));
        }
    }

    // constructor for test
    public Picks(List<Lotto> picks) {
        this.picks = picks;
        this.purchase = picks.size();
    }

    /**
     *
     * @param i index of picks
     * @return UserNumbersInfo
     */
    public UserNumbersInfo getNumbersPickAt(int i){
        return picks.get(i).get();
    }

    /**
     *
     * @return unmodifiable List <Lotto>
     */
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
