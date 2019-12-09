package hochenchong.exercises.chapter4;

import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.stream.Stream;

/**
 * Created by hochenchong on 2019/11/15
 */

public interface MyPerformance {
    public String getName();

    public Stream<Artist> getMusicians();

    default Stream<Artist> getAllMusicians() {
        return getMusicians().flatMap(artist -> Stream.concat(Stream.of(artist), artist.getMembers()));
    }
}
