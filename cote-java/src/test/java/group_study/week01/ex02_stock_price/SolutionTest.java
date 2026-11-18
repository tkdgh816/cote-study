package group_study.week01.ex02_stock_price;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SolutionTest {

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("stockPriceCases")
    void calculatesSecondsUntilPriceDrops(String description, int[] prices, int[] expected) {
        assertArrayEquals(expected, new Solution().solution(prices));
    }

    static Stream<Arguments> stockPriceCases() {
        return Stream.of(
                Arguments.of("공식 예제", new int[]{1, 2, 3, 2, 3},
                        new int[]{4, 3, 1, 1, 0}),
                Arguments.of("최소 길이: 가격 상승", new int[]{1, 2},
                        new int[]{1, 0}),
                Arguments.of("최소 길이: 가격 하락에도 1초 경과", new int[]{2, 1},
                        new int[]{1, 0}),
                Arguments.of("최소 길이: 동일 가격 유지", new int[]{1, 1},
                        new int[]{1, 0}),
                Arguments.of("계속 상승하면 끝까지 유지", new int[]{1, 2, 3, 4, 5},
                        new int[]{4, 3, 2, 1, 0}),
                Arguments.of("계속 하락하면 매번 1초 뒤 하락", new int[]{5, 4, 3, 2, 1},
                        new int[]{1, 1, 1, 1, 0}),
                Arguments.of("동일 가격은 하락으로 보지 않음", new int[]{3, 3, 3, 3, 3},
                        new int[]{4, 3, 2, 1, 0}),
                Arguments.of("동일 가격을 유지하다 하락", new int[]{3, 3, 3, 2, 2},
                        new int[]{3, 2, 1, 1, 0}),
                Arguments.of("마지막 시점에 여러 가격이 함께 하락", new int[]{2, 3, 4, 5, 1},
                        new int[]{4, 3, 2, 1, 0}),
                Arguments.of("상승 후 내려와도 기준 가격과 같으면 유지", new int[]{2, 4, 3, 2, 3},
                        new int[]{4, 1, 1, 1, 0}),
                Arguments.of("가격이 회복되어도 첫 하락에서 기간 종료", new int[]{5, 1, 5, 1, 5},
                        new int[]{1, 3, 1, 1, 0}),
                Arguments.of("직전 가격보다 내려가도 기준 가격 이상이면 유지", new int[]{3, 5, 4, 3, 2},
                        new int[]{4, 1, 1, 1, 0}),
                Arguments.of("여러 시점의 가격이 중간에 함께 하락", new int[]{2, 3, 4, 1, 5, 6},
                        new int[]{3, 2, 1, 2, 1, 0}),
                Arguments.of("최솟값과 최댓값이 반복", new int[]{1, 10_000, 1, 10_000, 1},
                        new int[]{4, 1, 2, 1, 0}),
                Arguments.of("최대 길이에서 최댓값 유지", repeatedPrices(100_000, 10_000),
                        secondsUntilEnd(100_000)),
                Arguments.of("최대 길이에서 마지막에만 하락", pricesDroppingAtEnd(100_000),
                        secondsUntilEnd(100_000))
        );
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
