package lotto;

import java.io.InputStream;
import org.junit.jupiter.api.AfterEach;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MachineTest {
    private final InputStream defaultIn = System.in;
    @AfterEach
    void resetInput() {
        System.setIn(defaultIn);
        camp.nextstep.edu.missionutils.Console.close();
    }

}
