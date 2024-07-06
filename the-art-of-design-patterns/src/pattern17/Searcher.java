package pattern17;

/**
 * 抽象查询接口
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public interface Searcher {
    /**
     * 查询数据
     *
     * @param userId 查询的id
     * @param keyword 查询的关键词
     * @return 查询结果
     */
    String doSearch(String userId, String keyword);
}
