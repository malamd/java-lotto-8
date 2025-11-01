package lotto.common;

public enum WinningDetails {
    FIRST(0,2000000000, "개 일치 (2,000,000,000원) -",6),
    SECOND(1,30000000, "개 일치 (30,000,000원) -",5),
    THIRD(2,1500000,"개 일치 (1,500,000원) -",5),
    FOURTH(3,50000,"개 일치 (50,000원) -",4),
    FIFTH(4,5000,"개 일치 (5,000원) -",3);


    private final int index;
    private final String message;
    private final int prize;
    private final int balls;

    WinningDetails(int index,int prize,String message,int balls) {
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
}
