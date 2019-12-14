package hochenchong.exercises.chapter9;

import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.function.Consumer;
import java.util.function.Function;

public class CallbackArtistAnalyser implements ArtistAnalyzer {

    private final Function<String, Artist> artistLookupService;

    public CallbackArtistAnalyser(Function<String, Artist> artistLookupService) {
        this.artistLookupService = artistLookupService;
    }

    @Override
    public void isLargerGroup(String artistName, String otherArtistName, Consumer<Boolean> handler) {
        Boolean b = getNumberOfMembers(artistName) > getNumberOfMembers(otherArtistName);
        handler.accept(b);
    }

    private long getNumberOfMembers(String artistName) {
        return artistLookupService.apply(artistName)
                .getMembers()
                .count();
    }
}
