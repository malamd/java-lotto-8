package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.dto.UserNumbersInfo;

public class Picks {
    private final List<Lotto> picks;
    private final int purchase;

    public Picks(int purchase){
        this.purchase = purchase;
        picks = new ArrayList<>();
        for (int i = 0; i < purchase; i++) {
            Set<Integer> uniqueNumbers = new HashSet<>();
            while (uniqueNumbers.size() < Lotto.getSize()) {
                int randomNumber = Randoms.pickNumberInRange(1, 45);
                uniqueNumbers.add(randomNumber);
            }
            picks.add(new Lotto(new ArrayList<>(uniqueNumbers)));
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
        return Collections.unmodifiableList(picks); // 외부에서 변경 불가능하게 unmodifiable list로 반환
    }


    @Override
    public String toString() {
        StringBuilder bf = new StringBuilder();
        for(int i = 0 ; i < purchase ; i++){
            bf.append(picks.get(i).toString());
            bf.append('\n');
        }
        return bf.toString();
    }

    public int size() {
        return purchase;
    }
}
