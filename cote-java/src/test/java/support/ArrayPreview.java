package support;

import java.util.Arrays;

public final class ArrayPreview {
    private ArrayPreview() {}

    public static String preview(int[] values) {
        if (values.length <= 8) {
            return Arrays.toString(values);
        }
        return "[%d, %d, %d, %d, ..., %d, %d, %d, %d] (길이 %d)".formatted(
                values[0], values[1], values[2], values[3],
                values[values.length - 4], values[values.length - 3],
                values[values.length - 2], values[values.length - 1], values.length);
    }
}
