package support;

import java.util.function.Supplier;

import org.junit.jupiter.api.function.ThrowingSupplier;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.fail;

public final class TestCaseOutput {
    private TestCaseOutput() {}

    public static <T> T runSolution(ThrowingSupplier<T> solution, Supplier<String> context) {
        return assertDoesNotThrow(solution, () -> {
            System.out.println(context.get());
            return "풀이 실행 중 예외 발생";
        });
    }

    public static void failComparison(String details) {
        System.out.println(details);
        fail();
    }
}
