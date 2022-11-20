package bridge.domain;

import bridge.BridgeRandomNumberGenerator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;


class BridgeTest {

    @DisplayName("BridgeMaker를 통해 생성된 다리가 정상적으로 할당된다")
    @Test
    void setBridgeWithBridgeMaker() {
        // given
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        int expectedSize = 3;

        // when
        Bridge bridge = new Bridge(bridgeMaker.makeBridge(expectedSize));

        // then
        assertThat(bridge.getSize()).isEqualTo(expectedSize);
    }

    @DisplayName("U, D 이외의 알파벳을 가진 다리가 할당되면 예외가 발생한다")
    @ParameterizedTest
    @MethodSource("generateData")
    void createBridgeWithWrongConditionBridge(List<String> steps) {
        assertThatThrownBy(() -> new Bridge(steps))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("U, D 이외의 방향을 가질 수 없습니다.");
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(Arrays.asList("A", "B", "A")),
                Arguments.of(Arrays.asList("1", "2", "3"))
        );
    }
}