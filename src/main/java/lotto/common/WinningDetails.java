package lotto.common;

public enum WinningDetails {
    FIRST(1,4,2000000000, "%d개 일치 (%s원) - %d개",6),
    SECOND(2,3,30000000, "%d개 일치, 보너스 볼 일치 (%s원) - %d개",5),
    THIRD(3,2,1500000,"%d개 일치 (%s원) - %d개",5),
    FOURTH(4,1,50000,"%d개 일치 (%s원) - %d개",4),
    FIFTH(5,0,5000,"%d개 일치 (%s원) - %d개",3);


    private final int rank;
    private final int index;
    private final String message;
    private final int prize;
    private final int balls;

    WinningDetails(int rank, int index,int prize,String message,int balls) {
        this.rank = rank;
        this.index = index;
        this.message = message;
        this.prize = prize;
        this.balls = balls;
    }

    public int getIndex() {
        return index;
    }

    public int getPrize() {
        return prize;
    }
    public String getMessage() {
        return message;
    }

    public int getBalls(){
        return balls;
    }

    public int getRank(){
        return rank;
    }
}
