package lotto.dto;

import java.util.Arrays;
import lotto.domain.Picks;

public class StatisticsInfo {
    //TODO: 구현
    private Integer[] winningCounts;
    float yieldPercentage;

    public StatisticsInfo(Integer[] winningCounts, float yieldPercentage){
        this.winningCounts = winningCounts;
        this.yieldPercentage = yieldPercentage;
    }

    public Integer[] getWinningCounts(){
        return Arrays.copyOf(winningCounts,winningCounts.length);
    }
    public float getYieldPercentage(){
        return yieldPercentage;
    }

}
