package group_study.week01.ex01_truck_crossing_bridge;

import java.util.Arrays;
import java.util.function.Supplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static support.ArrayPreview.preview;
import static support.TestCaseOutput.failComparison;
import static support.TestCaseOutput.runSolution;

class SolutionTest {

    @Test
    @DisplayName("공식 예제 1: 무게 제한으로 대기")
    void test01(TestInfo testInfo) {
        checkCrossingTime(testInfo.getDisplayName(), 2, 10,
                new int[]{7, 4, 5, 6}, 8);
    }

    @Test
    @DisplayName("공식 예제 2: 긴 다리를 건너는 트럭 한 대")
    void test02(TestInfo testInfo) {
        checkCrossingTime(testInfo.getDisplayName(), 100, 100,
                new int[]{10}, 101);
    }

    @Test
    @DisplayName("공식 예제 3: 모든 트럭이 연속 진입")
    void test03(TestInfo testInfo) {
        checkCrossingTime(testInfo.getDisplayName(), 100, 100,
                repeatedTrucks(10, 10), 110);
    }

    @Test
    @DisplayName("최소 입력")
    void test04(TestInfo testInfo) {
        checkCrossingTime(testInfo.getDisplayName(), 1, 1,
                new int[]{1}, 2);
    }

    @Test
    @DisplayName("길이 1인 다리에 매초 다음 트럭 진입")
    void test05(TestInfo testInfo) {
        checkCrossingTime(testInfo.getDisplayName(), 1, 10,
                new int[]{10, 1, 9, 2}, 5);
    }

    @Test
    @DisplayName("트럭 한 대의 무게가 제한과 같음")
    void test06(TestInfo testInfo) {
        checkCrossingTime(testInfo.getDisplayName(), 5, 10,
                new int[]{10}, 6);
    }

    @Test
    @DisplayName("다리 위 무게 합이 제한과 정확히 같음")
    void test07(TestInfo testInfo) {
        checkCrossingTime(testInfo.getDisplayName(), 3, 10,
                new int[]{6, 4}, 5);
    }

    @Test
    @DisplayName("모든 트럭이 한 대씩만 건널 수 있음")
    void test08(TestInfo testInfo) {
        checkCrossingTime(testInfo.getDisplayName(), 3, 10,
                new int[]{6, 6, 6}, 10);
    }

    @Test
    @DisplayName("무게 여유가 있어도 다리 길이만큼만 진입")
    void test09(TestInfo testInfo) {
        checkCrossingTime(testInfo.getDisplayName(), 2, 100,
                new int[]{1, 1, 1, 1, 1}, 7);
    }

    @Test
    @DisplayName("앞 트럭이 나간 초에 다음 트럭 진입")
    void test10(TestInfo testInfo) {
        checkCrossingTime(testInfo.getDisplayName(), 2, 10,
                new int[]{6, 4, 6, 4}, 6);
    }

    @Test
    @DisplayName("앞 트럭의 진입을 기다리는 동안 순서 유지")
    void test11(TestInfo testInfo) {
        checkCrossingTime(testInfo.getDisplayName(), 2, 10,
                new int[]{6, 5, 4}, 6);
    }

    @Test
    @DisplayName("다리 일부가 비어도 무게 제한 때문에 대기")
    void test12(TestInfo testInfo) {
        checkCrossingTime(testInfo.getDisplayName(), 3, 10,
                new int[]{6, 4, 5}, 7);
    }

    @Test
    @DisplayName("무거운 트럭과 가벼운 트럭이 번갈아 진입")
    void test13(TestInfo testInfo) {
        checkCrossingTime(testInfo.getDisplayName(), 3, 10,
                new int[]{7, 3, 7, 3}, 8);
    }

    @Test
    @DisplayName("최대 다리 길이를 건너는 트럭 한 대")
    void test14(TestInfo testInfo) {
        checkCrossingTime(testInfo.getDisplayName(), 10_000, 10_000,
                new int[]{10_000}, 10_001);
    }

    @Test
    @DisplayName("최대 개수의 트럭이 최대 길이 다리에 연속 진입")
    void test15(TestInfo testInfo) {
        // 첫 트럭은 1초에 진입하고 마지막 트럭은 10,000초에 진입합니다.
        checkCrossingTime(testInfo.getDisplayName(), 10_000, 10_000,
                repeatedTrucks(10_000, 1), 20_000);
    }

    @Test
    @DisplayName("최대 개수의 트럭이 최대 길이 다리를 한 대씩 통과")
    void test16(TestInfo testInfo) {
        // 한 대마다 다리 길이만큼 기다리므로 총 시간은 1 + 트럭 수 * 다리 길이입니다.
        checkCrossingTime(testInfo.getDisplayName(), 10_000, 10_000,
                repeatedTrucks(10_000, 10_000), 100_000_001);
    }

    private void checkCrossingTime(String description, int bridgeLength,
                                   int weight, int[] truckWeights, int expected) {
        Supplier<String> message = () -> "입력값 bridge_length=" + bridgeLength + ", weight=" + weight
                + ", truck_weights=" + preview(truckWeights) + "\n총 소요 시간 기댓값=" + expected;
        int actual = runSolution(
                () -> new Solution().solution(bridgeLength, weight, truckWeights.clone()), message);
        if (expected != actual) {
            failComparison(message.get() + "\n결괏값=" + actual);
        }
        System.out.println("[통과] " + description + "\n" + message.get());
    }

    private static int[] repeatedTrucks(int count, int truckWeight) {
        int[] trucks = new int[count];
        Arrays.fill(trucks, truckWeight);
        return trucks;
    }
}
