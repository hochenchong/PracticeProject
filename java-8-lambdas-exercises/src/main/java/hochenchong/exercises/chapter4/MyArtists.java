package hochenchong.exercises.chapter4;

import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.List;
import java.util.Optional;

/**
 * Created by hochenchong on 2019/11/19
 */

public class MyArtists {
    private List<Artist> artists;

    public MyArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Optional<Artist> getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            return Optional.empty();
        }
        return Optional.of(artists.get(index));
    }

    public String getArtistName(int index) {
        Optional<Artist> artistOptional = getArtist(index);
        if (artistOptional.isPresent()) {
            return artistOptional.get().getName();
        }

        return "unknown";
    }
    /*
    书中附带网站的解法：
    public String getArtistName1(int index) {
        Optional<Artist> artist = getArtist(index);
        return artist.map(Artist::getName).orElse("unknown");
    }
    */

}
