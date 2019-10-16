package hochenchong.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author HochenChong
 * @date 2019/09/26
 */
public class OptionalTest {
    @Test
    public void testOptional() {
        Optional<String> optional = Optional.ofNullable("hello");

        /*if (optional.isPresent()) {
            System.out.println(optional.get());
        }*/
        optional.ifPresent(System.out::println);

        System.out.println("--------------");

        List<A> aList = Arrays.asList(new A("zhangsan", 10), new A("lisi", 15));
//        Optional<B> bOptional = Optional.ofNullable(new B("b", null));
        Optional<B> bOptional = Optional.ofNullable(null);
//        Optional<B> bOptional = Optional.ofNullable(new B("b", aList));

        List<A> aList1 = bOptional.map(b -> b.getaList()).orElse(Collections.emptyList());
        System.out.println(aList1);
    }

    class A {
        private String name;
        private int age;

        public A(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    class B {
        private String name;
        private List<A> aList;

        public B(String name, List<A> aList) {
            this.name = name;
            this.aList = aList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<A> getaList() {
            return aList;
        }

        public void setaList(List<A> aList) {
            this.aList = aList;
        }
    }
}
