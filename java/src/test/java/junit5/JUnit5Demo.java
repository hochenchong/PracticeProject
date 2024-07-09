package junit5;

import org.junit.jupiter.api.*;

/**
 * JUnit 5 使用 demo
 *
 * @author hochenchong
 * @date 2024/07/09
 */
@DisplayName("JUnit5的demo")
public class JUnit5Demo {

    @BeforeAll
    public static void init() {
        System.out.println("@BeforeAll 必须是静态方法，只初始化一次");
    }

    @AfterAll
    public static void cleanup() {
        System.out.println("@AfterAll 必须是静态方法，最后执行");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("---------------------------");
        System.out.println("@BeforeEach 每个测试方法前运行");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("@AfterEach 每个测试方法后运行");
        System.out.println("---------------------------");
    }

    @DisplayName("第一个测试方法")
    @Test
    public void testFirst() {
        System.out.println("第一个测试方法");
    }

    @DisplayName("第二个测试方法")
    @Disabled // 禁用执行测试
    @Test
    public void testTwo() {
        System.out.println("第二个测试方法");
    }

    // @RepeatedTest 重复性测试，
    @DisplayName("第三个测试方法")
    @RepeatedTest(value = 3, name = RepeatedTest.LONG_DISPLAY_NAME)
    public void testThree() {
        System.out.println("第三个测试方法");
    }

    // @Nested 内嵌测试类
    @Nested
    @DisplayName("第一个内嵌测试类")
    public class FirstNestTest {
        @Test
        public void test() {
            System.out.println("第一个内嵌测试类执行测试");
        }
    }
}