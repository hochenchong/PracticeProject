package structural.proxy;

/**
 * 具体查询类
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public class RealSearcher implements Searcher {
    @Override
    public String doSearch(String userId, String keyword) {
        System.out.println("用户 '" + userId + "' 使用关键词 '" + keyword + "' 查询信息");
        return "具体内容";
    }
}