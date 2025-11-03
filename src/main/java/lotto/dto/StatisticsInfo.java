package lotto.dto;

import java.util.Arrays;

/**
 * 최종 당첨 통계와 총 수익률 정보를 담아 전달하는 데이터 전송 객체(DTO)입니다.
 */
public class StatisticsInfo {
    private Integer[] winningCounts;
    float yieldPercentage;

    /**
     * StatisticsInfo 객체를 생성합니다.
     *
     * @param winningCounts   각 등수별 당첨 횟수를 담은 배열.
     * @param yieldPercentage 계산된 총 수익률(%).
     */
    public StatisticsInfo(Integer[] winningCounts, float yieldPercentage) {
        this.winningCounts = winningCounts;
        this.yieldPercentage = yieldPercentage;
    }

    /**
     * 각 등수별 당첨 횟수가 담긴 배열의 복사본을 반환합니다.
     *
     * @return 당첨 횟수 배열의 복사본.
     */
    public Integer[] getWinningCounts() {
        return Arrays.copyOf(winningCounts, winningCounts.length);
    }

    /**
     * 총 수익률을 반환합니다.
     *
     * @return 총 수익률(%).
     */
    public float getYieldPercentage() {
        return yieldPercentage;
    }

}
