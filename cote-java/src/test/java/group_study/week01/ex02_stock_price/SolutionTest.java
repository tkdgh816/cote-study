package group_study.week01.ex02_stock_price;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.function.Supplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static support.ArrayPreview.preview;
import static support.TestCaseOutput.failComparison;
import static support.TestCaseOutput.runSolution;

class SolutionTest {

    @Test
    @DisplayName("공식 예제")
    void test01(TestInfo testInfo) {
        checkDurations(testInfo.getDisplayName(), new int[]{1, 2, 3, 2, 3},
                new int[]{4, 3, 1, 1, 0});
    }

    @Test
    @DisplayName("최소 길이: 가격 상승")
    void test02(TestInfo testInfo) {
        checkDurations(testInfo.getDisplayName(), new int[]{1, 2},
                new int[]{1, 0});
    }

    @Test
    @DisplayName("최소 길이: 가격 하락에도 1초 경과")
    void test03(TestInfo testInfo) {
        checkDurations(testInfo.getDisplayName(), new int[]{2, 1},
                new int[]{1, 0});
    }

    @Test
    @DisplayName("최소 길이: 동일 가격 유지")
    void test04(TestInfo testInfo) {
        checkDurations(testInfo.getDisplayName(), new int[]{1, 1},
                new int[]{1, 0});
    }

    @Test
    @DisplayName("계속 상승하면 끝까지 유지")
    void test05(TestInfo testInfo) {
        checkDurations(testInfo.getDisplayName(), new int[]{1, 2, 3, 4, 5},
                new int[]{4, 3, 2, 1, 0});
    }

    @Test
    @DisplayName("계속 하락하면 매번 1초 뒤 하락")
    void test06(TestInfo testInfo) {
        checkDurations(testInfo.getDisplayName(), new int[]{5, 4, 3, 2, 1},
                new int[]{1, 1, 1, 1, 0});
    }

    @Test
    @DisplayName("동일 가격은 하락으로 보지 않음")
    void test07(TestInfo testInfo) {
        checkDurations(testInfo.getDisplayName(), new int[]{3, 3, 3, 3, 3},
                new int[]{4, 3, 2, 1, 0});
    }

    @Test
    @DisplayName("동일 가격을 유지하다 하락")
    void test08(TestInfo testInfo) {
        checkDurations(testInfo.getDisplayName(), new int[]{3, 3, 3, 2, 2},
                new int[]{3, 2, 1, 1, 0});
    }

    @Test
    @DisplayName("마지막 시점에 여러 가격이 함께 하락")
    void test09(TestInfo testInfo) {
        checkDurations(testInfo.getDisplayName(), new int[]{2, 3, 4, 5, 1},
                new int[]{4, 3, 2, 1, 0});
    }

    @Test
    @DisplayName("상승 후 내려와도 기준 가격과 같으면 유지")
    void test10(TestInfo testInfo) {
        checkDurations(testInfo.getDisplayName(), new int[]{2, 4, 3, 2, 3},
                new int[]{4, 1, 1, 1, 0});
    }

    @Test
    @DisplayName("가격이 회복되어도 첫 하락에서 기간 종료")
    void test11(TestInfo testInfo) {
        checkDurations(testInfo.getDisplayName(), new int[]{5, 1, 5, 1, 5},
                new int[]{1, 3, 1, 1, 0});
    }

    @Test
    @DisplayName("직전 가격보다 내려가도 기준 가격 이상이면 유지")
    void test12(TestInfo testInfo) {
        checkDurations(testInfo.getDisplayName(), new int[]{3, 5, 4, 3, 2},
                new int[]{4, 1, 1, 1, 0});
    }

    @Test
    @DisplayName("여러 시점의 가격이 중간에 함께 하락")
    void test13(TestInfo testInfo) {
        checkDurations(testInfo.getDisplayName(), new int[]{2, 3, 4, 1, 5, 6},
                new int[]{3, 2, 1, 2, 1, 0});
    }

    @Test
    @DisplayName("최솟값과 최댓값이 반복")
    void test14(TestInfo testInfo) {
        checkDurations(testInfo.getDisplayName(), new int[]{1, 10_000, 1, 10_000, 1},
                new int[]{4, 1, 2, 1, 0});
    }

    @Test
    @DisplayName("최대 길이에서 최댓값 유지")
    void test15(TestInfo testInfo) {
        checkDurations(testInfo.getDisplayName(), repeatedPrices(100_000, 10_000),
                secondsUntilEnd(100_000));
    }

    @Test
    @DisplayName("최대 길이에서 마지막에만 하락")
    void test16(TestInfo testInfo) {
        checkDurations(testInfo.getDisplayName(), pricesDroppingAtEnd(100_000),
                secondsUntilEnd(100_000));
    }

    private void checkDurations(String description, int[] prices, int[] expected) {
        String input = "입력값 prices=" + preview(prices);
        Supplier<String> message = () -> input + "\n기댓값=" + preview(expected);
        int[] actual = runSolution(
                () -> new Solution().solution(prices.clone()), message);
        if (actual == null) {
            failComparison(message.get() + "\n결괏값=null");
        }
        int index = Arrays.mismatch(expected, actual);
        if (index >= 0) {
            StringBuilder detail = new StringBuilder(message.get())
                    .append("\n결괏값=").append(preview(actual))
                    .append("\n첫 불일치 인덱스=").append(index)
                    .append("\n결과 길이(기댓값=%d, 결괏값=%d)\n".formatted(expected.length, actual.length));
            int lastIndex = Math.max(expected.length, actual.length) - 1;
            for (int i = Math.max(0, index - 4); i <= Math.min((long) index + 4, lastIndex); i++) {
                detail.append("\n%s [%d] 기댓값=%s | 결괏값=%s | 입력값 prices[%d]=%s".formatted(
                        i == index ? ">>" : "  ", i, valueAt(expected, i), valueAt(actual, i), i, valueAt(prices, i)));
            }
            failComparison(detail.toString());
        }
        System.out.println("[통과] " + description + "\n" + message.get());
    }

    private static String valueAt(int[] values, int index) {
        return index < values.length ? Integer.toString(values[index]) : "원소 없음";
    }

    private static int[] repeatedPrices(int count, int price) {
        int[] prices = new int[count];
        Arrays.fill(prices, price);
        return prices;
    }

    private static int[] pricesDroppingAtEnd(int count) {
        int[] prices = repeatedPrices(count, 10_000);
        prices[count - 1] = 1;
        return prices;
    }

    private static int[] secondsUntilEnd(int count) {
        return IntStream.range(0, count).map(index -> count - 1 - index).toArray();
    }
}
