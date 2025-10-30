package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성

    @Test
    void inputMoneyValueTest(){
        LottoMachine lm = new LottoMachine();

        String str = "8000\n";
        System.setIn(new ByteArrayInputStream(str.getBytes()));

        lm.inputMoney();

        int result = lm.getCount();

        assertThat(result)
                .isPositive()
                .isEqualTo(8);
    }

    //1-45사이를 제외한 숫자 개입시
    @Test
    void LottoConditionTest(){
        assertThatThrownBy(() -> new Lotto(List.of(46, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void machineParameterTest(){
        LottoMachine lm = new LottoMachine();

        String str = "8000\n1,2,3,4,5,6\n7\n";
        System.setIn(new ByteArrayInputStream(str.getBytes()));

        lm.run();

        assertThat(lm.getCount()).isEqualTo(8);
        assertThat(lm.getGoalLotto().getLotto()).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(lm.getBonusNum()).isEqualTo(7);
    }



}
