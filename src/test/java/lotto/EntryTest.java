package lotto;

import java.io.InputStream;
import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntryTest {
    private final InputStream defaultIn = System.in;
    @AfterEach
    void resetInput() {
        System.setIn(defaultIn);
        camp.nextstep.edu.missionutils.Console.close();
    }

}
