package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Picks;

/**
 * 구매한 로또의 개수와 로또 번호 목록 정보를 담아 전달하는 데이터 전송 객체(DTO)입니다.
 */
public class EntriesInfo {
    private final int entries;
    private final Picks picks;

    /**
     * EntriesInfo 객체를 생성합니다.
     *
     * @param entries 구매한 로또의 총 개수.
     * @param numberPicks 구매한 로또들의 묶음 객체.
     */
    public EntriesInfo(int entries, Picks numberPicks){
        this.entries = entries;
        this.picks = numberPicks;
    }

    /**
     * 구매한 로또의 총 개수를 반환합니다.
     *
     * @return 로또 구매 개수.
     */
    public int getEntries(){
        return entries;
    }

    /**
     * 구매한 모든 로또 객체의 수정 불가능한 리스트를 반환합니다.
     *
     * @return 수정 불가능한 로또 리스트.
     */
    public List<Lotto> getPicks(){
        return picks.getPicks();
    }

}
