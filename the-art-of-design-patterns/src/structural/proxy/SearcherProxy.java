package structural.proxy;

/**
 * @author hochenchong
 * @date 2024/07/06
 */
public class SearcherProxy implements Searcher {
    private final AccessValidator accessValidator = new AccessValidator();
    private final Logger logger = new Logger();
    private final RealSearcher realSearcher = new RealSearcher();

    @Override
    public String doSearch(String userId, String keyword) {
        // 身份验证
        if (accessValidator.validate(userId)) {
            String result = realSearcher.doSearch(userId, keyword);
            logger.log(userId);
            return result;
        }
        return "";
    }
}
