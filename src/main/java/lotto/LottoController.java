package lotto;

// 실제 로또 당첨 통계를 계산해주는 클래스 입니다
public class LottoController {
    private final Picks picks;
    private final WinningNumbers winningNumbers;

    public LottoController(Picks picks, WinningNumbers winningNumbers){
        this.picks = picks;
        this.winningNumbers = winningNumbers;
    }
}
