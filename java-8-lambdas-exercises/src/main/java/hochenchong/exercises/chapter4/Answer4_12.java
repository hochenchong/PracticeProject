package hochenchong.exercises.chapter4;

/**
 * Created by hochenchong on 2019/11/15
 */

public class Answer4_12 {

    /*
    1.
    例 4-25  表示音乐表演的接口
    该接口表示艺术家的演出——专辑或演唱会
    public interface Performance {
        public String getName();
        public Stream<Artist> getMusicians();
    }
    添加 getAllMusicians 方法，该方法返回包含所有艺术家名字的 Stream
    如果对象是乐队，则返回每个乐队成员的名字。
    答案看：hochenchong.chapter4.MyPerformance
     */

    /*
    2. 根据前面描述的重载解析规则，能否重写默认方法中的 equals 或 hashCode 方法？
    答：不重写，equals 或 hashCode 是在 java.lang.Object 中定义。根据三定律中的第一条，类胜于接口。

    三定律：
            1. 类胜于接口。如果在继承链中有方法体或抽象的方法声明，那么就可以忽略接口中定义的方法。
            2. 子类胜于父类。如果一个接口继承了另一个接口，且两个接口都定义了一个默认方法，那么子类中定义的方法胜出。
            3. 没有规则三。如果上面两条规则不适用，子类要么需要实现该方法，要么将该方法声明为抽象方法。
            其中第一条规则是为了让代码向后兼容。
     */

    /*
    3. 例 4-26 所示的 Artists 类表示了一组艺术家，重构该类
    使得 getArtist 方法返回一个 Optional<Artist> 对象。如果索引在有效范围内，返回对应的元素，
    否则返回一个空 Optional 对象。此外，还需重构 getArtistName 方法，保持相同的行为。

    例 4-26　包含多个艺术家的 Artists 类
    public class Artists {
        private List<Artist> artists;
        public Artists(List<Artist> artists) {
            this.artists = artists;
        }
        public Artist getArtist(int index) {
            if (index < 0 || index >= artists.size()) {
                indexException(index);
            }
            return artists.get(index);
        }
        private void indexException(int index) {
            throw new IllegalArgumentException(index +
                    "doesn't correspond to an Artist");
        }
        public String getArtistName(int index) {
            try {
                Artist artist = getArtist(index);
                return artist.getName();
            } catch (IllegalArgumentException e) {
                return "unknown";
            }
        }
    }

    答案见 hochenchong.chapter4.MyArtists
    */
}
