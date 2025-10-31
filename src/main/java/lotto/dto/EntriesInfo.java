package lotto.dto;

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
    public String getPicks(){
        return picks.toString();
    }

}
