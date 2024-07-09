package junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * JUnit 5 新的断言类 org.junit.jupiter.api.Assertions
 *     支持 Lambda 表达式
 *
 * @author hochenchong
 * @date 2024/07/09
 */
public class AssertionsTest {

    @DisplayName("分组断言")
    @Test
    public void testGroupAssertions() {
        int[] numbers = {0, 1, 2, 3, 4};
        Assertions.assertAll("numbers",
                () -> Assertions.assertEquals(numbers[1], 1),
                () -> Assertions.assertEquals(numbers[2], 2),
                () -> Assertions.assertEquals(numbers[3], 3)
        );
    }

    @DisplayName("超时方法测试")
    @Test
    public void testTimeoutPreemptively() {
        Assertions.assertTimeoutPreemptively(Duration.of(1, ChronoUnit.SECONDS), () -> Thread.sleep(500));
    }

    @DisplayName("捕获异常测试")
    @Test
    public void testThrowsException() {
        String str = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> Integer.parseInt(str));
    }
}