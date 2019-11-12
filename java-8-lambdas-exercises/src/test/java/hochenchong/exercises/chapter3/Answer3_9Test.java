package hochenchong.exercises.chapter3;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.Track;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Created by hochenchong on 2019/11/12
 */
public class Answer3_9Test {
    // 3.9.1.a 求和函数
    @Test
    public void testAddUp() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        Assert.assertEquals(6, Answer3_9.addUp(integerList.stream()));
    }

    // 3.9.1.b 接受艺术家列表作为参数，返回一个字符串列表，其中包含艺术家的姓名和国籍
    @Test
    public void testGetArtistNameAndNationality() {
        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist("zhangsan", "China"));
        artists.add(new Artist("lisi", "UK"));
        artists.add(new Artist("wangwu", "US"));
        assertEquals(Arrays.asList("zhangsan-China", "lisi-UK", "wangwu-US"), Answer3_9.getArtistNameAndNationality(artists));
    }

    // 1.c 接受专辑列表作为参数，返回一个由最多包含 3 首歌曲的专辑组成的列表
    @Test
    public void testGetAlbumLessThreeSongs() {
        List<Album> albumList = new ArrayList<>();
        Album album = new Album("a", Arrays.asList(new Track("aa", 1), new Track("aaa", 2)), new ArrayList<Artist>());
        Album album1 = new Album("b",
                Arrays.asList(new Track("bb", 1), new Track("bbb", 2), new Track("bbbb", 3), new Track("bbbbb", 4)),
                new ArrayList<Artist>());
        albumList.add(album);
        albumList.add(album1);
        List<Album> albumLessThreeSongs = Answer3_9.getAlbumLessThreeSongs(albumList);
        Assert.assertEquals(1, albumLessThreeSongs.size());
        Assert.assertEquals(2, albumLessThreeSongs.get(0).getTrackList().size());
    }

    // 6. 计算一个字符串中小写字母的个数
    @Test
    public void testGetStringLowerCaseCount() {
        String string = "ahAelZZloZzb";
        Assert.assertEquals(8, Answer3_9.getStringLowerCaseCount(string));
    }

    // 7. 在一个字符串列表中，找出包含最多小写字母的字符串。对于空列表，返回 Optional<String> 对象。
    @Test
    public void testFindMostLowerCase() {
        List<String> strings = Arrays.asList("aaa", "BBbbbB", "cCCCCC", "dddDDDdd");
        Assert.assertEquals("dddDDDdd", Answer3_9.findMostLowerCase(strings).get());

        Optional<String> stringOptional = Answer3_9.findMostLowerCase(new ArrayList<>());
        System.out.println(stringOptional.isPresent());
    }
}