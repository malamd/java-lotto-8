package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.dto.UserNumbersInfo;

/**
 * 사용자가 구매한 여러 개의 로또(Lotto) 객체를 관리합니다.
 */
public class Picks {
    private final List<Lotto> picks;
    private final int purchase;

    /**
     * 구매한 로또 개수만큼 무작위 번호로 로또를 생성하여 초기화합니다.
     * 이 생성자는 실제 애플리케이션 로직에서 사용됩니다.
     *
     * @param purchase 구매할 로또의 개수.
     */
    public Picks(int purchase){
        this.purchase = purchase;
        picks = new ArrayList<>();
        for (int i = 0; i < purchase; i++) {
            List<Integer> uniqueNumbers = Randoms.pickUniqueNumbersInRange(1,45,6);
            picks.add(new Lotto(uniqueNumbers));
        }
    }

    /**
     * 미리 생성된 로또 리스트로 초기화합니다.
     * 이 생성자는 주로 테스트 코드에서 특정 시나리오를 검증하기 위해 사용됩니다.
     *
     * @param picks 테스트에 사용할 로또 객체 리스트.
     */
    public Picks(List<Lotto> picks) {
        this.picks = picks;
        this.purchase = picks.size();
    }

    /**
     * 지정된 인덱스에 해당하는 로또의 번호 정보를 반환합니다.
     * 반환되는 객체는 원본 데이터를 수정할 수 없는 DTO(UserNumbersInfo)입니다.
     *
     * @param i 조회할 로또의 인덱스 (0부터 시작).
     * @return 해당 로또의 번호 정보를 담은 UserNumbersInfo 객체.
     */
    public UserNumbersInfo getNumbersPickAt(int i){
        return picks.get(i).get();
    }

    /**
     * Picks가 관리하는 모든 로또 객체의 수정 불가능한 리스트를 반환합니다.
     *
     * @return 수정이 불가능한 로또 객체 리스트.
     */
    public List<Lotto> getPicks() {
        return Collections.unmodifiableList(picks);
    }


    /**
     * 구매한 모든 로또의 번호를 각 줄에 하나씩 출력 형식에 맞는 문자열로 변환합니다.
     * 예: [8, 21, 23, 41, 42, 43]
     *     [3, 5, 11, 16, 32, 38]
     *
     * @return 모든 로또 번호가 포함된, 줄바꿈으로 구분된 문자열.
     */
    @Override
    public String toString() {
        return picks.stream()
                .map(Lotto::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     * 구매한 로또의 총 개수를 반환합니다.
     *
     * @return 구매한 로또의 개수.
     */
    public int size() {
        return purchase;
    }
}
