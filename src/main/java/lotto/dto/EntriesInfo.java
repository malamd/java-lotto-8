package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Picks;

public class EntriesInfo {
    private final int entries;
    private final Picks picks;

    public EntriesInfo(int entries, Picks numberPicks){
        this.entries = entries;
        this.picks = numberPicks;
    }

    public String getEntries(){
        return Integer.toString(entries);
    }
    public List<Lotto> getPicks(){
        return picks.getPicks();
    }

}
