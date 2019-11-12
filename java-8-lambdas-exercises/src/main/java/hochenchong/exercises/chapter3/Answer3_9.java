package hochenchong.exercises.chapter3;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hochenchong on 2019/11/12
 * 《Java 8 函数式编程》第三章 3.9 练习题
 */
public class Answer3_9 {

    // 1.a 求和函数
    public static int addUp(Stream<Integer> numbers) {
        return numbers.mapToInt(value -> value).sum();
        // 书中网站的答案是:
        // return numbers.reduce(0, (a, b) -> a + b);
    }

    // 1.b 接受艺术家列表作为参数，返回一个字符串列表，其中包含艺术家的姓名和国籍
    public static List<String> getArtistNameAndNationality(List<Artist> artistList) {
        return artistList.stream().map(artist -> artist.getName() + "-" + artist.getNationality()).collect(Collectors.toList());
        /*
        书中网站的答案是：https://github.com/RichardWarburton/java-8-lambdas-exercises/blob/master/src/main/java/com/insightfullogic/java8/answers/chapter3/Question1.java
            return artists.stream()
                      .flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))
                      .collect(Collectors.toList());
         */
    }


    // 1.c 接受专辑列表作为参数，返回一个由最多包含 3 首歌曲的专辑组成的列表
    public static List<Album> getAlbumLessThreeSongs(List<Album> albumList) {
        return albumList.stream().filter(album -> album.getTracks().count() <= 3).collect(Collectors.toList());
    }

    // 2. 将外部迭代转换为内部迭代
    /*
    int totalMembers = 0;
    for (Artist artist : artists) {
        Stream<Artist> members = artist.getMembers();
        totalMembers += members.count();
    }
     */
    public static int getArtistMemberSum(List<Artist> artists) {
        return (int) artists.stream().flatMap(artist -> artist.getMembers()).count();
    }
    // 书中网站的另一种答案是
    /*
    public static int countBandMembersInternal(List<Artist> artists) {
        // NB: readers haven't learnt about primitives yet, so can't use the sum() method
        return artists.stream()
                       .map(artist -> artist.getMembers().count())
                       .reduce(0L, Long::sum)
                       .intValue();

        //return (int) artists.stream().flatMap(artist -> artist.getMembers()).count();
    }
     */

    /*
     3. 根据 Stream 方法的签名，判断其是惰性求值还是及早求值
        a. 及早求值
        b. 惰性求值
     */

    /*
    4. 高阶函数。下面的 Stream 函数是高阶函数吗？为什么？
        a. Yes.高阶函数是指接受另外一个函数作为参数，或返回一个函数的函数。
        b. No
     */

    /*
    5.
        a. 无副作用
        b. 有副作用，改变了程序状态，forEach 方法，它是一个终结方法，会对外部的 AtomicInteger 进行计数
     */

    /*
    6. 计算一个字符串中小写字母的个数（提示：参阅 String 对象的 chars 方法）
     */
    public static int getStringLowerCaseCount(String string) {
        // a-z 97-122
        // return string.chars().filter(value -> (value >= 97 && value <= 122)).map(value -> 1).sum();
        return string.chars().filter(value -> Character.isLowerCase(value)).map(value -> 1).sum();

        // 书中网站的答案是:
        /*
        return (int) string.chars()
                           .filter(Character::isLowerCase)
                           .count();
         */
    }

    /*
    7. 在一个字符串列表中，找出包含最多小写字母的字符串。对于空列表，返回 Optional<String> 对象。
     */
    public static Optional<String> findMostLowerCase(List<String> stringList) {
        return stringList.stream().reduce((a, b) -> {
            return getStringLowerCaseCount(a) - getStringLowerCaseCount(b) >= 0 ? a : b;
        });

        // 书中网站的答案是:
        /*
        return strings.stream()
                      .max(Comparator.comparing(StringExercises::countLowercaseLetters));
         */
        // 相当于这里的：
        // return stringList.stream().max(Comparator.comparing(string -> getStringLowerCaseCount(string)));
    }
}
