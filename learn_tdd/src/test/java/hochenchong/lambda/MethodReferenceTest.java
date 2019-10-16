package hochenchong.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author HochenChong
 * @date 2019/09/28
 */
public class MethodReferenceTest {
    @Test
    public void testMethod() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        list.forEach(System.out::println);

        System.out.println("------------------");
        list.sort((a, b) -> b.compareToIgnoreCase(a));
        list.forEach(System.out::print);

        System.out.println("------------------");
        list.sort(String::compareToIgnoreCase);
        list.forEach(System.out::print);

        System.out.println("------------------");

    }
}
