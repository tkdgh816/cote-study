package group_study.week01.ex01_truck_crossing_bridge;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("bridgeCrossingCases")
    void calculatesTimeUntilAllTrucksCross(String description, int bridgeLength,
                                         int weight, int[] truckWeights, int expected) {
        assertEquals(expected, new Solution().solution(bridgeLength, weight, truckWeights));
    }

    static Stream<Arguments> bridgeCrossingCases() {
        return Stream.of(
                Arguments.of("공식 예제 1: 무게 제한으로 대기", 2, 10,
                        new int[]{7, 4, 5, 6}, 8),
                Arguments.of("공식 예제 2: 긴 다리를 건너는 트럭 한 대", 100, 100,
                        new int[]{10}, 101),
                Arguments.of("공식 예제 3: 모든 트럭이 연속 진입", 100, 100,
                        repeatedTrucks(10, 10), 110),
                Arguments.of("최소 입력", 1, 1,
                        new int[]{1}, 2),
                Arguments.of("길이 1인 다리에 매초 다음 트럭 진입", 1, 10,
                        new int[]{10, 1, 9, 2}, 5),
                Arguments.of("트럭 한 대의 무게가 제한과 같음", 5, 10,
                        new int[]{10}, 6),
                Arguments.of("다리 위 무게 합이 제한과 정확히 같음", 3, 10,
                        new int[]{6, 4}, 5),
                Arguments.of("모든 트럭이 한 대씩만 건널 수 있음", 3, 10,
                        new int[]{6, 6, 6}, 10),
                Arguments.of("무게 여유가 있어도 다리 길이만큼만 진입", 2, 100,
                        new int[]{1, 1, 1, 1, 1}, 7),
                Arguments.of("앞 트럭이 나간 초에 다음 트럭 진입", 2, 10,
                        new int[]{6, 4, 6, 4}, 6),
                Arguments.of("앞 트럭의 진입을 기다리는 동안 순서 유지", 2, 10,
                        new int[]{6, 5, 4}, 6),
                Arguments.of("다리 일부가 비어도 무게 제한 때문에 대기", 3, 10,
                        new int[]{6, 4, 5}, 7),
                Arguments.of("무거운 트럭과 가벼운 트럭이 번갈아 진입", 3, 10,
                        new int[]{7, 3, 7, 3}, 8),
                Arguments.of("최대 다리 길이를 건너는 트럭 한 대", 10_000, 10_000,
                        new int[]{10_000}, 10_001),
                // 첫 트럭은 1초에 진입하고 마지막 트럭은 10,000초에 진입합니다.
                Arguments.of("최대 개수의 트럭이 최대 길이 다리에 연속 진입", 10_000, 10_000,
                        repeatedTrucks(10_000, 1), 20_000),
                // 한 대마다 다리 길이만큼 기다리므로 총 시간은 1 + 트럭 수 * 다리 길이입니다.
                Arguments.of("최대 개수의 트럭이 최대 길이 다리를 한 대씩 통과", 10_000, 10_000,
                        repeatedTrucks(10_000, 10_000), 100_000_001)
        );
    }

    private static int[] repeatedTrucks(int count, int truckWeight) {
        int[] trucks = new int[count];
        Arrays.fill(trucks, truckWeight);
        return trucks;
    }
}
