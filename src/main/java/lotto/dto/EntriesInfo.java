package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Picks;

// 발행한 로또 번호에 관한 정보를 저장하는 클래스 입니다.
public class EntriesInfo {
    private final int entries;
    private final Picks picks;

    public EntriesInfo(int entries, Picks numberPicks){
        this.entries = entries;
        this.picks = numberPicks;
    }

    public int getEntries(){
        return entries;
    }
    public List<Lotto> getPicks(){
        return picks.getPicks();
    }

}
