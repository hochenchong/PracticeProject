package hochenchong.exercises.chapter9;

import java.util.function.Consumer;

public interface ArtistAnalyzer {
    public void isLargerGroup(String artistName,
                              String otherArtistName,
                              Consumer<Boolean> handler);
}
